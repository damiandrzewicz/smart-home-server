/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.ComponentScan;

/**
 *
 * @author damian
 */
//Issue: @SpringBootTest for a non-spring-boot application
//Soultion: This can be achieved by creating a TestConfig inner class and annotate it with @SpringBootConfiguration
//Note that TestConfig class also has @ComponentScan annotation. This is used by Spring to find your applicacion beans
//https://stackoverflow.com/questions/39858226/springboottest-for-a-non-spring-boot-application
@SpringBootConfiguration
//@ComponentScan("com.moderndev.smarthome.data")

//Issue: java.lang.IllegalStateException: Failed to load ApplicationContext
//Caused by: java.lang.IllegalStateException: Unable to retrieve @EnableAutoConfiguration base packages
//Soultion: A workaround for this is to add @AutoConfigurationPackage to the @SpringBootConfiguration class 
//that's needed because there's no @SpringBootApplication class
//https://github.com/spring-projects/spring-boot/issues/10465
@AutoConfigurationPackage

public class TestConfig {
    
}
