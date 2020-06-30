/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message;

import java.util.List;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 *
 * @author damian
 */
@Component
@Slf4j
@Getter
public class MessageSubscribtions {
    
    @Value("${myhome.mqtt.subscribtions}")
    private List<String> subscribtions;
    
    @PostConstruct
    private void postConstruct(){
        log.info("loaded mqtt subscribtions: " + subscribtions.toString());
    }
}
