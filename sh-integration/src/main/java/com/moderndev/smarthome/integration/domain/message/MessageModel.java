/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageModel extends MessageNameModel{
    
    private MessageResultModel result = new MessageResultModel();
    
    private JsonNode context;
}
