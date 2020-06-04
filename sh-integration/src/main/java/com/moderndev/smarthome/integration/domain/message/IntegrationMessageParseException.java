/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.message;

/**
 *
 * @author damian
 */
public class IntegrationMessageParseException extends Exception {
 
    public IntegrationMessageParseException(String message){
        super(message);
    }
        
    public IntegrationMessageParseException(String message, Throwable cause){
        super(message, cause);
    }
}
