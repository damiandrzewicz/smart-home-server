/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.services;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 *
 * @author damian
 */
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttOutboundService {
    
    void sendToMqtt(String data, @Header(MqttHeaders.TOPIC) String topic);
}
