/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moderndev.smarthome.integration.message.ContextProcessingException;
import com.moderndev.smarthome.integration.utils.ValidatorHelper;
import com.moderndev.smarthome.integration.message.Request;
import com.moderndev.smarthome.integration.services.messages.QueryTasksService;
import javax.validation.Validator;
import org.springframework.stereotype.Component;

/**
 *
 * @author damian
 */

/*
Request:
{
    "messageName":"queryTaskRequest"
}

If password Ok
Response:
{
    "messageName":"loginResponse",
    "result":
    {
        "state":"ok"
    }
    content:
    {
        //device specified content
    }
*/

@Component
public class QueryTaskRequest extends Request{
    
    private QueryTasksService queryTasksService;

    public QueryTaskRequest(QueryTasksService queryTasksService, ObjectMapper objectMapper, Validator validator, ValidatorHelper validatorHelper) {
        super(objectMapper, validator, validatorHelper);
        this.queryTasksService = queryTasksService;
    }


    @Override
    protected JsonNode processContext(String receiverId, JsonNode context) throws ContextProcessingException {
        
        //TODO find messages for
        JsonNode contextOut = queryTasksService.getTaskContext(receiverId);
        
        return contextOut;
    }
    
}
