/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moderndev.smarthome.integration.domain.message.common.HealthReportRequestContextModel;
import com.moderndev.smarthome.integration.message.ContextProcessingException;
import com.moderndev.smarthome.integration.message.MessageFactory;
import com.moderndev.smarthome.integration.message.Request;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Validator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.SmartValidator;

/**
 *
 * @author damian
 * 
 */


/*
If ok
Request:
{
    "messageName":"healthReportRequest",
    "context":
    {
        "state":"ok"
    }
}

If error
Request:
{
    "messageName":"healthReportRequest",
    "context":
    {
        "state":"error"
        "message":"some message error",
    }
}

Response:
{
    "messageName":"healthReportResponse",
    "result":
    {
        "state":"ok"
    }
}

*/
@Component
//@Scope("prototype")
public class HealthReportRequest extends Request{
    
    public HealthReportRequest(ObjectMapper objectMapper, MessageFactory messageFactory, Validator validator) {
        
        super(objectMapper, messageFactory, validator);
        setMessageName("healthReportRequest");
        registerMessgeInFactory();
    }
    
    @Override
    protected JsonNode processContext(JsonNode context) throws ContextProcessingException {

        HealthReportRequestContextModel healthReportContextModel;
                
        try {
            healthReportContextModel = getObjectMapper().treeToValue(context, HealthReportRequestContextModel.class);
        } catch (JsonProcessingException ex) {
            throw new ContextProcessingException(ex);
        }
            
        if(healthReportContextModel.getState().isError()) {
            String latestError = healthReportContextModel.getLatestError();
            //TODO save somewhere to process it
        }
        
        return null;
        
    }
    
}
