/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.message;

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
public class MessageOperationModel {

    private String operation;
    
    public MessageDirection getDirection(){
        return this.getOperation().contains("Request") ? MessageDirection.Request : MessageDirection.Response;
    }
    
    public String getOppositeOperation(){
        String tempOperation = new String(this.operation);
        
        if(tempOperation.contains("Request")){
            tempOperation.replace("Request", "Response");
        } else {
            tempOperation.replace("Response", "Request");
        }
        
        return tempOperation;
    }
}
