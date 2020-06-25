/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moderndev.smarthome.integration.domain.message.MessageRootModel;
import com.moderndev.smarthome.integration.domain.message.topic.TopicModel;
import com.moderndev.smarthome.integration.domain.mqtt.MqttMessageModel;
import com.moderndev.smarthome.integration.message.topic.TopicEsp;
import com.moderndev.smarthome.integration.message.topic.TopicProcessingException;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
/**
 *
 * @author damian
 */
@Getter
@Setter
@Slf4j
public abstract class Request implements Message{
     
    private ObjectMapper objectMapper;
    
    private int qos = 0;

    public Request(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
 
    @Override
    public MqttMessageModel process(MqttMessageModel mqttMessageModelIn) throws MqttMessgeProcessingException{
        
        String topicIn = mqttMessageModelIn.getTopic();
        String payloadIn = mqttMessageModelIn.getPayload();
        
        MessageRootModel rootModelIn = null;
        MessageRootModel rootModelOut = new MessageRootModel();
        
        MqttMessageModel mqttMessageModelOut = new MqttMessageModel();
        mqttMessageModelOut.setQos(getQos());
      
        try {
            TopicModel topicModelIn = new TopicEsp().parse(topicIn);
            
            TopicModel topicModelOut = new TopicModel();
            topicModelOut.setDomain(topicModelIn.getDomain());
            topicModelOut.setReceiverId(topicModelIn.getSenderId()); 
            topicModelOut.setSenderId(topicModelIn.getReceiverId());
            
            String topicOut = new TopicEsp().build(topicModelOut);
            
            mqttMessageModelOut.setTopic(topicOut);
        
            rootModelIn = objectMapper.readValue(payloadIn, MessageRootModel.class);
            
            rootModelOut.setOperation(rootModelIn.getOppositeOperation());
            
        } catch (TopicProcessingException | JsonProcessingException ex) {
            log.error(ex.getMessage());
            throw new MqttMessgeProcessingException(ex.getMessage());
        } 
            
        try {
            JsonNode contextOut = processContext(rootModelIn.getContext());
            if(contextOut != null){
                rootModelOut.setContext(contextOut);
            }
        } catch (ContextProcessingException ex) {
            rootModelOut.getResult().setError("context processing exception");
            log.error(ex.getMessage());
        }

        rootModelOut.getResult().setOk();

        try {
            String payloadOut = objectMapper.writeValueAsString(rootModelOut);
            mqttMessageModelOut.setPayload(payloadOut);

        } catch (JsonProcessingException ex) {
            log.error(ex.getMessage());
            throw new MqttMessgeProcessingException(ex.getMessage());
        }
        
        return mqttMessageModelOut;
    }
    
    protected abstract JsonNode processContext(JsonNode context) throws ContextProcessingException;
    
}
