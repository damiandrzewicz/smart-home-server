/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.services.mqtt;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.moderndev.smarthome.integration.domain.mqtt.MqttMessageModel;
import com.moderndev.smarthome.integration.domain.message.MessageOperationModel;
import com.moderndev.smarthome.integration.message.Message;
import com.moderndev.smarthome.integration.message.MessageFactory;
import com.moderndev.smarthome.integration.message.MqttMessgeProcessingException;

/**
 *
 * @author damian
 */
@Slf4j
@Service
public class MqttRequestDispatcher{
    
    private MqttOutboundService mqttOutboundService;

    private MessageFactory messageFactory;

    public MqttRequestDispatcher(MqttOutboundService mqttOutboundService, MessageFactory messageFactory) {
        this.mqttOutboundService = mqttOutboundService;
        this.messageFactory = messageFactory;
    }


    public void dispatch(MqttMessageModel mqttMessageIn) {
        
        log.debug(mqttMessageIn.toString());
        
        MqttMessageModel mqttMessageOut = null;
        
        try {
            String payload = mqttMessageIn.getPayload();
            
            MessageOperationModel basePayload = new ObjectMapper().readValue(payload, MessageOperationModel.class);
            String operation = basePayload.getOperation();
                    
            Message message = messageFactory.create(operation);
            mqttMessageOut = message.process(mqttMessageIn);
           
        } catch (JsonProcessingException ex) {
            log.error(ex.getMessage());
            return;
        } catch (MqttMessgeProcessingException ex) {
            log.error(ex.getMessage());
            return;
        }

        mqttOutboundService.publish(mqttMessageOut.getTopic(), mqttMessageOut.getQos(), mqttMessageOut.getPayload());
    }
}
