/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message;

import com.moderndev.smarthome.integration.services.utils.BeanUtil;
import org.springframework.stereotype.Component;

/**
 *
 * @author damian
 */
@Component
public class MessageFactory {
    
    public static Message create(String operation){
        return BeanUtil.getBean(operation, Message.class);
    }
}
