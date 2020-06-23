/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message;

/**
 *
 * @author damian
 */
public class MqttMessgeProcessingException extends Exception {

    public MqttMessgeProcessingException(String message) {
        super(message);
    }
    
}
