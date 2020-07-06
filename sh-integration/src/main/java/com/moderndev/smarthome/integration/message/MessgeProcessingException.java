/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message;

import lombok.Getter;

/**
 *
 * @author damian
 */
@Getter
public class MessgeProcessingException extends Exception {
    
    
    public MessgeProcessingException(String message){
        super(message);
    }
    
}
