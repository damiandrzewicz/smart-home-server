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
public class ContextProcessingException extends Exception {
    
    public ContextProcessingException(String message) {
        super(message);
    }

    public ContextProcessingException(Throwable cause) {
        super(cause);
    }

}
