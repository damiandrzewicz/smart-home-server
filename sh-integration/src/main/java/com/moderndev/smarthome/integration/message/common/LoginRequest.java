/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moderndev.smarthome.integration.domain.message.common.LoginContextModel;
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
Request:
{
    "messageName":"loginRequest",
    "context":
    {
        "login":"mylogin123",
        "password":"mypassword123"
    }
}

If password Ok
Response:
{
    "messageName":"loginResponse",
    "result":
    {
        "state":"ok"
    }
}

If password wrong
Response:
{
    "messageName":"loginResponse",
    "result":
    {
        "state":"error",
        "message":"wrong credentials"
    }
}

*/
@Component
//@Scope("prototype")
public class LoginRequest extends Request{

    public LoginRequest(ObjectMapper objectMapper, MessageFactory messageFactory) {
        super(objectMapper, messageFactory);
        setMessageName("loginRequest");
        registerMessgeInFactory();
    }
    
    @Override
    protected JsonNode processContext(JsonNode context) throws ContextProcessingException, JsonProcessingException {
        
        LoginContextModel loginContextModel = getObjectMapper().treeToValue(context, LoginContextModel.class);

        //process in service
        
        return null;
    }
    
}
