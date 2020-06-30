/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moderndev.smarthome.integration.domain.mqtt.MqttMessageModel;

/**
 *
 * @author damian
 */
public abstract class Response extends Message{

    public Response(ObjectMapper objectMapper, MessageFactory messageFactory) {
        super(objectMapper, messageFactory);
    }
    
    @Override
    public MqttMessageModel process(MqttMessageModel mqttMessageModelIn) throws MessgeProcessingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
