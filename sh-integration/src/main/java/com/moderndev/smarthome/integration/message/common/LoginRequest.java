/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moderndev.smarthome.integration.domain.message.common.LoginRequestContextModel;
import com.moderndev.smarthome.integration.message.ContextProcessingException;
import com.moderndev.smarthome.integration.message.MessageFactory;
import com.moderndev.smarthome.integration.message.Request;
import com.moderndev.smarthome.integration.message.ViolationStringBuilder;
import com.moderndev.smarthome.integration.services.messages.LoginService;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;

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
@Slf4j
public class LoginRequest extends Request{
    
    private LoginService loginService;

    public LoginRequest(LoginService loginService, ObjectMapper objectMapper, 
            MessageFactory messageFactory, Validator validator) {
        
        super(objectMapper, messageFactory, validator);
        this.loginService = loginService;
        
        setMessageName("loginRequest");
        registerMessgeInFactory();
    }
    
    @Override
    protected JsonNode processContext(JsonNode context) throws ContextProcessingException {
        
        LoginRequestContextModel loginContextModel;
        try {
            loginContextModel = getObjectMapper().treeToValue(context, LoginRequestContextModel.class);
        } catch (JsonProcessingException ex) {
            throw new ContextProcessingException(ex);
        }
        
        Set<ConstraintViolation<LoginRequestContextModel>> violations = getValidator().validate(loginContextModel);
        if(!violations.isEmpty()){
            log.error("an exception occurred!", violations.toString());
            throw new ContextProcessingException(ViolationStringBuilder.buildString(violations));
        }

        if(!loginService.login(loginContextModel.getLogin(), loginContextModel.getPassword())){
            throw new ContextProcessingException("wrong credentials");
        }
        
        return null;
    }
    
}
