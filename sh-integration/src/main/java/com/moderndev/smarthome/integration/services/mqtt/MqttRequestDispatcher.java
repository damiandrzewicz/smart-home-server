/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.services.mqtt;

import com.moderndev.smarthome.integration.domain.message.IntegrationMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author damian
 */
@Slf4j
@Service
public class MqttRequestDispatcher{
    
    @Autowired
    private MqttOutboundService mqttOutboundService;
    
    public void dispatch(String topic, String data) {
        
        log.debug("topic:[{}]", topic);
        log.debug("data:[{}]", data);
        
        //get message by topic
        IntegrationMessage integrationMessage = null;
        
        //parse message
        //integrationMessage.parse(data);
        
        //publish response
        mqttOutboundService.publish(topic, 0, data);
        
        //
        
//        if(topic.equals("myhome/srv/base/auth/esp32_f96db0")){
//            AuthorizationMessage authorizationMessage = new AuthorizationMessage();
//            
//            try{
//                authorizationMessage.parse(data);
//                mqttOutboundService.publish(
//                        "myhome/node/base/auth/esp32_f96db0", 
//                        1, 
//                        authorizationMessage.getOk());
//            }catch(JSONException ex){
//                
//                log.error("exception: %s", ex.getMessage(), ex);
//                mqttOutboundService.publish(
//                        "myhome/node/base/auth/esp32_f96db0", 
//                        1, 
//                        authorizationMessage.getError(ex.getMessage()));
//            }
//        }
        

    }
    
}
