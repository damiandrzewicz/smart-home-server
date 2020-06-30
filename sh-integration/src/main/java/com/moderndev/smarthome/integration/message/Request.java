/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moderndev.smarthome.integration.domain.message.MessageModel;
import com.moderndev.smarthome.integration.domain.message.topic.TopicModel;
import com.moderndev.smarthome.integration.domain.mqtt.MqttMessageModel;
import com.moderndev.smarthome.integration.message.topic.TopicEsp;
import com.moderndev.smarthome.integration.message.topic.TopicProcessingException;
import javax.validation.Validator;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.SmartValidator;
/**
 *
 * @author damian
 */
@Getter
@Setter
@Slf4j
public abstract class Request extends Message{

    public Request(ObjectMapper objectMapper, MessageFactory messageFactory, Validator validator) {
        super(objectMapper, messageFactory, validator);
    }
     
    
    @Override
    public MqttMessageModel process(MqttMessageModel mqttMessageModelIn) throws MessgeProcessingException{
        
        String error;
        
        if(mqttMessageModelIn == null){
            error = "'mqttMessageModelIn' nullpointer";
            log.error(error);
            throw new MessgeProcessingException(error);
        }
        
        if(mqttMessageModelIn.getTopic() == null){
            error = "'mqttMessageModelIn.topic' nullpointer";
            log.error(error);
            throw new MessgeProcessingException(error);
        }
        
        if(mqttMessageModelIn.getPayload()== null){
            error = "'mqttMessageModelIn.payload' nullpointer";
            log.error(error);
            throw new MessgeProcessingException(error);
        }
        
        String topicIn = mqttMessageModelIn.getTopic();
        String payloadIn = mqttMessageModelIn.getPayload();
        
        MessageModel rootModelIn = null;
        MessageModel rootModelOut = new MessageModel();
        
        MqttMessageModel mqttMessageModelOut = new MqttMessageModel();
        mqttMessageModelOut.setQos(getResponseQos());
        
        TopicModel topicModelIn;
      
        try {
            topicModelIn = new TopicEsp().parse(topicIn);
            
            TopicModel topicModelOut = new TopicModel();
            topicModelOut.setDomain(topicModelIn.getDomain());
            topicModelOut.setReceiverId(topicModelIn.getSenderId()); 
            topicModelOut.setSenderId(topicModelIn.getReceiverId());
            
            String topicOut = new TopicEsp().build(topicModelOut);
            mqttMessageModelOut.setTopic(topicOut);
        
            rootModelIn = getObjectMapper().readValue(payloadIn, MessageModel.class);
            rootModelOut.setMessageName(rootModelIn.getOppositeMessageName());
            
        } catch (TopicProcessingException | JsonProcessingException ex) {
            log.error(ex.getMessage());
            throw new MessgeProcessingException(ex.getMessage());
        } 
        
        JsonNode contextIn = rootModelIn.getContext();
        if(contextIn != null){
            try {
                JsonNode contextOut = processContext(rootModelIn.getContext());
                if(contextOut != null){
                    rootModelOut.setContext(contextOut);
                }

                rootModelOut.getResult().setOk();
            } catch (ContextProcessingException ex) {
                rootModelOut.getResult().setError(ex.getMessage());
                log.error("an exception occurred!", ex);
            }
        }
            
        try {
            String payloadOut = getObjectMapper().writeValueAsString(rootModelOut);
            mqttMessageModelOut.setPayload(payloadOut);

        } catch (JsonProcessingException ex) {
            log.error(ex.getMessage());
            throw new MessgeProcessingException(ex.getMessage());
        }
        
        return mqttMessageModelOut;
    }
    
    protected abstract JsonNode processContext(JsonNode context) throws ContextProcessingException;
    

}
