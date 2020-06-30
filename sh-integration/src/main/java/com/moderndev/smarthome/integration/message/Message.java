/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moderndev.smarthome.integration.domain.mqtt.MqttMessageModel;
import javax.validation.Validator;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.SmartValidator;
/**
 *
 * @author damian
 */
@Getter
@Setter
@NonFinal
@Slf4j
public abstract class Message {
    
    private ObjectMapper objectMapper;
    
    private MessageFactory messageFactory;
    
    Validator validator;
    
    private String messageName;
    
    private int responseQos = 0;

    public Message(ObjectMapper objectMapper, MessageFactory messageFactory, Validator validator) {
        this.objectMapper = objectMapper;
        this.messageFactory = messageFactory;
        this.validator = validator;
    }
    

    
    protected void registerMessgeInFactory(){
        this.messageFactory.addAllowedMessage(getMessageName());
    }
    
    public abstract MqttMessageModel process(MqttMessageModel mqttMessageModelIn) 
            throws MessgeProcessingException;
}
