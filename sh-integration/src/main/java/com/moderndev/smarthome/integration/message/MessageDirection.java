/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message;

import java.util.NoSuchElementException;

/**
 *
 * @author damian
 */
public enum MessageDirection {
    Request("req"),
    Response("res");
    
    private String value;

    private MessageDirection(String value) {
        this.value = value;
    }
    
    public String getString(){
        return value;
    }

    @Override
    public String toString() {
        return "TopicDirection{" + "value=" + value + '}';
    }
    
    
    static public MessageDirection parse(String string){
        for(MessageDirection messageDirection : MessageDirection.values()){
            if(messageDirection.getString().equals(string)){
                return messageDirection;
            }
        }
        
        throw new NoSuchElementException("element '" + string + "' has not been found");
    }
    
}
