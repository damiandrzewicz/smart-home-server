package com.moderndev.smarthome.integration;

import com.moderndev.smarthome.integration.services.MqttOutboundService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SmartHomeIntegrationApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(SmartHomeIntegrationApplication.class, args);
        MqttOutboundService mqtt = ctx.getBean(MqttOutboundService.class);
        mqtt.sendToMqtt("test from spring", "/topic5/asd");
    }
}
