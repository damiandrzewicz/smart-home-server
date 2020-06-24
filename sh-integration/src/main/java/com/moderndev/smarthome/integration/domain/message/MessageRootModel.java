/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.message;

import com.fasterxml.jackson.databind.JsonNode;
import com.moderndev.smarthome.integration.message.MessageDirection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author damian
 */
@Getter
@Setter
public class MessageRootModel extends MessageOperationModel{
    
    private MessageResultModel result = new MessageResultModel();
    
    private JsonNode context;
}
