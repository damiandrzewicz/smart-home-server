/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message;

import com.moderndev.smarthome.integration.services.utils.BeanUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *
 * @author damian
 */
@Component
@Slf4j
public class MessageFactory {
    
    private static final Set<String> messages = new HashSet<>();
    
    public static Message create(String message) throws MessageFactoryException{
        if(isOperationAllowed(message)){
            return BeanUtil.getBean(message, Message.class);
        } else {
            throw new MessageFactoryException("message not supported: " + message);
        }
    }
    
    private static boolean isOperationAllowed(String operation){
        synchronized(messages){
            return messages.stream().filter(o -> o.equals(operation)).findAny().isPresent();
        }
    }
    
    public static void addAllowedMessage(String messge){
        synchronized(messages){
            log.info("registering message: " + messge);
            messages.add(messge);
        }
    }
}
