/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moderndev.smarthome.integration.domain.message.common.HealthReportContextModel;
import com.moderndev.smarthome.integration.message.ContextProcessingException;
import com.moderndev.smarthome.integration.message.MessageFactory;
import com.moderndev.smarthome.integration.message.Request;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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

    public HealthReportRequest(ObjectMapper objectMapper, MessageFactory messageFactory) {
        super(objectMapper, messageFactory);
        setMessageName("healthReportRequest");
        registerMessgeInFactory();
    }
    
    @Override
    protected JsonNode processContext(JsonNode context) throws ContextProcessingException, JsonProcessingException {

        HealthReportContextModel healthReportContextModel
                = getObjectMapper().treeToValue(context, HealthReportContextModel.class);
            
        //TODO process in service
        
        return null;
        
    }
    
}
