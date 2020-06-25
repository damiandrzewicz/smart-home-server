/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message;
import com.moderndev.smarthome.integration.domain.mqtt.MqttMessageModel;
/**
 *
 * @author damian
 */
public interface Message {
    
    MqttMessageModel process(MqttMessageModel mqttMessageModelIn) throws MessgeProcessingException;
}
