/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message;

import com.moderndev.smarthome.integration.utils.BeanUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author damian
 */
@Component
@Slf4j
public class MessageFactory {
    
    @Value("${myhome.messages.allowed}")
    private Set<String> messages = new HashSet<>();
    
    @PostConstruct
    private void postConstruct(){
        log.info("registered messages:[{}]", messages);
    }
    
    public Message create(String message) throws MessageFactoryException{
        if(isOperationAllowed(message)){
            return BeanUtil.getBean(message, Message.class);
        } else {
            throw new MessageFactoryException("message not supported: " + message);
        }
    }
    
    private boolean isOperationAllowed(String operation){
        synchronized(messages){
            return messages.stream().filter(o -> o.equals(operation)).findAny().isPresent();
        }
    }
}
