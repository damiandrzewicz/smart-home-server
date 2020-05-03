/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.configuration;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageHandler;

/**
 *
 * @author damian
 */
@Slf4j
@Configuration
@EnableIntegration
public class MqttConfig {
    
    @Value("${mqtt.host:localhost}")
    private String host;
    
    @Value("${mqtt.port:1883}")
    private Integer port;
    
    @Value("${mqtt.username}")
    private String username;
    
    @Value("${mqtt.password}")
    private String password;
    
    public MqttPahoClientFactory mqttClientFactory(){
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        
        String fullHost = String.format("tcp://{}:{}", host, port);
        log.info("Creating Mqtt factory: host=[{}]", fullHost);
        options.setServerURIs(new String[] {fullHost});
        
        //Setting credentials if available
        if(username != null && !username.isEmpty()
                && password != null && !password.isEmpty()){
            options.setUserName(username);
            options.setPassword(password.toCharArray());
        }

        factory.setConnectionOptions(options);
        return factory;
    }
    
    @Bean
    public MessageProducer mqttInbound(){
        MqttPahoMessageDrivenChannelAdapter adapter = 
                new MqttPahoMessageDrivenChannelAdapter("testClientInbound", mqttClientFactory());
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        //adapter.setOutputChannel(mqttInputChannel());
        adapter.setOutputChannelName("mqttInputChannel");
        adapter.addTopic("/topic1");
        adapter.addTopic("/topic2");
        return adapter;
    }
    
    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound(){
        MqttPahoMessageHandler handler = new MqttPahoMessageHandler("testClientOutbound", mqttClientFactory());
        handler.setAsync(true);
        handler.setDefaultTopic("testTopic");
        return handler;
    }
}
