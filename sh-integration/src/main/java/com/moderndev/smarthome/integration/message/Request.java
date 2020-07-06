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
import com.moderndev.smarthome.integration.utils.ValidatorHelper;
import java.util.Set;
import javax.validation.ConstraintViolation;
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

    public Request(ObjectMapper objectMapper, ValidatorHelper validatorHelper) {
        super(objectMapper, validatorHelper);
    }


    @Override
    public MqttMessageModel process(MqttMessageModel mqttMessageModelIn) throws MessgeProcessingException{
        
        String violationsString = getValidatorHelper().checkViolations(mqttMessageModelIn);
        if(violationsString != null){
            throw new MessgeProcessingException(violationsString);
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
        
        try {
            JsonNode contextOut = processContext(
                    topicModelIn.getSenderId(),
                    rootModelIn.getContent());
            if(contextOut != null){
                rootModelOut.setContent(contextOut);
            }

            rootModelOut.getResult().setOk();
        } catch (ContextProcessingException ex) {
            rootModelOut.getResult().setError(ex.getMessage());
            log.error("an exception occurred!", ex);
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
    
    protected abstract JsonNode processContext(String receiverId, JsonNode context) throws ContextProcessingException;
    

}
