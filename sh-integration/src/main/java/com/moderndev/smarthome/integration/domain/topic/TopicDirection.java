/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.topic;

import java.util.NoSuchElementException;

/**
 *
 * @author damian
 */
public enum TopicDirection {
    Request("req"),
    Response("res");
    
    private String value;

    private TopicDirection(String value) {
        this.value = value;
    }
    
    public String getString(){
        return value;
    }

    @Override
    public String toString() {
        return "TopicDirection{" + "value=" + value + '}';
    }
    
    
    static public TopicDirection parse(String string){
        for(TopicDirection topicDirection : TopicDirection.values()){
            if(topicDirection.getString().equals(string)){
                return topicDirection;
            }
        }
        
        throw new NoSuchElementException("element '" + string + "' has not been found");
    }
    
}
