/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moderndev.smarthome.integration.domain.message.common.SenderContextModel;
import com.moderndev.smarthome.integration.domain.message.topic.TopicModel;
import com.moderndev.smarthome.integration.message.ContextProcessingException;
import com.moderndev.smarthome.integration.utils.ValidatorHelper;
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

    public QueryTaskRequest(QueryTasksService queryTasksService, ObjectMapper objectMapper, Validator validator, ValidatorHelper validatorHelper) {
        super(objectMapper, validator, validatorHelper);
        this.queryTasksService = queryTasksService;
    }


    @Override
    protected JsonNode processContext(JsonNode context) throws ContextProcessingException {
        
        SenderContextModel senderContextModel;
        try {
            senderContextModel = getObjectMapper().treeToValue(context, SenderContextModel.class);
        } catch (JsonProcessingException ex) {
            throw new ContextProcessingException(ex);
        }
        
        String violationsString = getValidatorHelper().checkViolations(getValidator().validate(senderContextModel));
        if(violationsString != null){
            throw new ContextProcessingException(violationsString);
        }
        
        //TODO find messages for
        JsonNode contextOut = queryTasksService.getTaskContext(senderContextModel.getSenderId());
        
        return contextOut;
    }
    
}
