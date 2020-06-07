package com.moderndev.smarthome;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
public class SmartHomeMainApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SmartHomeMainApp.class, args);
        //mqtt.sendToMqtt("test from spring", "/topic5/asd");
    }
}
