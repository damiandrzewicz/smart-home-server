/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author damian
 */
@Configuration
//@PropertySource( value = { "classpath:application-dev.properties" })
public class IntegrationModuleConfig {
    
    @Configuration
    @PropertySource("classpath:application.properties")
    @Profile("default")
    static class Defaultconfig{}
    
    @Configuration
    @PropertySource("classpath:application-dev.properties")
    @Profile("dev")
    static class DevelopmentConfig{}
}
