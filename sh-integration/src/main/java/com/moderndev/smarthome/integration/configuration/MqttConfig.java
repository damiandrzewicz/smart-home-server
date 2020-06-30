/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.configuration;

import com.moderndev.smarthome.integration.message.MessageSubscribtions;
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
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;

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
    
    @Value("${mqtt.port:1993}")
    private Integer port;
    
    @Value("${mqtt.username:}")
    private String username;
    
    @Value("${mqtt.password:}")
    private String password;
    
    private MessageSubscribtions messageSubscribtions;

    public MqttConfig(MessageSubscribtions messageSubscribtions) {
        this.messageSubscribtions = messageSubscribtions;
    }

    
    public MqttPahoClientFactory mqttClientFactory(){
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        
        String fullHost = String.format("tcp://%s:%s", host, port);
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
                new MqttPahoMessageDrivenChannelAdapter("myHome_srv_inbound", mqttClientFactory());
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        //adapter.setOutputChannel(mqttInputChannel());
        adapter.setOutputChannelName("mqttInputChannel");
        
        messageSubscribtions.getSubscribtions()
                .stream()
                .forEach(s -> adapter.addTopic(s));
        
        return adapter;
    }
    
    @Bean
    @ServiceActivator(inputChannel = "mqttOutboundChannel")
    public MessageHandler mqttOutbound(){
        MqttPahoMessageHandler handler = new MqttPahoMessageHandler("myHome_srv_outbound", mqttClientFactory());
        handler.setAsync(true);
        handler.setDefaultTopic("defaultTopic");
        return handler;
    }
}
