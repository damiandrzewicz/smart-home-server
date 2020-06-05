/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.services.mqtt;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.ServiceActivator;

import org.springframework.messaging.MessageHandler;

import org.springframework.stereotype.Service;

/**
 *
 * @author damian
 */
@Slf4j
@Service
public class MqttInboundService {
    
    @Autowired
    private MqttRequestDispatcher mqttRequestDispatcher;
    
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler(){
        log.debug("here!");
        
        MessageHandler handler = (msg) ->{
            log.debug("here123!");
            String topic = msg.getHeaders().get("mqtt_receivedTopic").toString();
            String data = msg.getPayload().toString();
            mqttRequestDispatcher.dispatch(topic, data);
        };

        return handler;
    }
}
