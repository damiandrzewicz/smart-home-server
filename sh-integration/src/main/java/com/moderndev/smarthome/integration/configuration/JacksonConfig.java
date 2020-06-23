/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

/**
 *
 * @author damian
 */
@Configuration
public class JacksonConfig {
    
    @Bean
    public Jackson2ObjectMapperBuilder configureObjectMapper(){
        Jackson2ObjectMapperBuilder jomb = new Jackson2ObjectMapperBuilder();
        jomb.failOnUnknownProperties(false);
        return jomb;
    }
    
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
