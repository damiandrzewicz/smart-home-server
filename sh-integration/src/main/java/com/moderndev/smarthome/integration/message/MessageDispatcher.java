/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.moderndev.smarthome.integration.domain.mqtt.MqttMessageModel;
import com.moderndev.smarthome.integration.domain.message.MessageNameModel;
import com.moderndev.smarthome.integration.message.Message;
import com.moderndev.smarthome.integration.message.MessageFactory;
import com.moderndev.smarthome.integration.message.MessgeProcessingException;
import com.moderndev.smarthome.integration.services.mqtt.MqttOutboundService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.annotation.DependsOn;

/**
 *
 * @author damian
 */
@Slf4j
@Service
public class MessageDispatcher{
    
    private MqttOutboundService mqttOutboundService;

    private MessageFactory messageFactory;

    public MessageDispatcher(MqttOutboundService mqttOutboundService, MessageFactory messageFactory) {
        this.mqttOutboundService = mqttOutboundService;
        this.messageFactory = messageFactory;
    }


    public void dispatch(MqttMessageModel mqttMessageIn) {
        
        log.debug(mqttMessageIn.toString());
        
        MqttMessageModel mqttMessageOut = null;
        
        try {
            String payload = mqttMessageIn.getPayload();
            
            MessageNameModel basePayload = new ObjectMapper().readValue(payload, MessageNameModel.class);
            String messageName = basePayload.getMessageName();
                    
            Message message = messageFactory.create(messageName);
            mqttMessageOut = message.process(mqttMessageIn);
           
        } catch (JsonProcessingException ex) {
            log.error("an exception occurred!", ex);
            return;
        } catch (MessgeProcessingException ex) {
            log.error("an exception occurred!", ex);
            return;
        } catch (MessageFactoryException ex) {
            log.error("an exception occurred!", ex);
            return;
        }

        mqttOutboundService.publish(mqttMessageOut.getTopic(), mqttMessageOut.getQos(), mqttMessageOut.getPayload());
    }
}
