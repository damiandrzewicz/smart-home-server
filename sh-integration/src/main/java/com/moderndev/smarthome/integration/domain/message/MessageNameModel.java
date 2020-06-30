/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.message;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.moderndev.smarthome.integration.message.MessageDirection;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.NonFinal;

/**
 *
 * @author damian
 */
@Getter
@Setter
@NonFinal
public class MessageNameModel {

    private String messageName;
    
    @JsonIgnore
    public MessageDirection getDirection(){
        return this.getMessageName().contains("Request") ? MessageDirection.Request : MessageDirection.Response;
    }
    
    @JsonIgnore
    public String getOppositeMessageName(){
        
        if(getMessageName().contains("Request")){
            return getMessageName().replace("Request", "Response");
        } else {
            return getMessageName().replace("Response", "Request");
        }

    }
}
