/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moderndev.smarthome.integration.domain.message.common.QueryTasksRequestContextModel;
import com.moderndev.smarthome.integration.domain.message.topic.TopicModel;
import com.moderndev.smarthome.integration.message.ContextProcessingException;
import com.moderndev.smarthome.integration.message.MessageFactory;
import com.moderndev.smarthome.integration.message.Request;
import com.moderndev.smarthome.integration.services.messages.QueryTasksService;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Validator;
import org.springframework.stereotype.Component;

/**
 *
 * @author damian
 */
@Component
public class QueryTaskRequest extends Request{
    
    private QueryTasksService queryTasksService;

    public QueryTaskRequest(ObjectMapper objectMapper, MessageFactory messageFactory, 
            Validator validator, QueryTasksService queryTasksService) {
        super(objectMapper, messageFactory, validator);
        this.queryTasksService = queryTasksService;
        
        setMessageName("queryTaskRequest");
        registerMessgeInFactory();
    }
    
    

    @Override
    protected JsonNode processContext(JsonNode context) throws ContextProcessingException {
        
        QueryTasksRequestContextModel queryTasksRequestContextModel;
        try {
            queryTasksRequestContextModel = getObjectMapper().treeToValue(context, QueryTasksRequestContextModel.class);
        } catch (JsonProcessingException ex) {
            throw new ContextProcessingException(ex);
        }
        
        //TODO find messages for
        String senderId = queryTasksRequestContextModel.getSenderId();
        JsonNode contextOut = queryTasksService.getTaskContext(senderId);
    }
    
}
