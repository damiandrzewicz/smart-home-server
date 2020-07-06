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
import com.moderndev.smarthome.integration.utils.ValidatorHelper;
import com.moderndev.smarthome.integration.message.MessageFactory;
import com.moderndev.smarthome.integration.message.Request;
import com.moderndev.smarthome.integration.services.messages.LoginService;
import javax.validation.Validator;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class LoginRequest extends Request{
    
    private LoginService loginService;

    public LoginRequest(LoginService loginService, ObjectMapper objectMapper, Validator validator, ValidatorHelper validatorHelper) {
        super(objectMapper, validator, validatorHelper);
        this.loginService = loginService;
    }


    @Override
    protected JsonNode processContext(JsonNode context) throws ContextProcessingException {
        
                
        LoginRequestContextModel loginContextModel;
        try {
            loginContextModel = getObjectMapper().treeToValue(context, LoginRequestContextModel.class);
        } catch (JsonProcessingException ex) {
            throw new ContextProcessingException(ex);
        }
                
        String violationsString = getValidatorHelper().checkViolations(getValidator().validate(loginContextModel));
        if(violationsString != null){
            throw new ContextProcessingException(violationsString);
        }

        if(!loginService.login(loginContextModel.getLogin(), loginContextModel.getPassword())){
            throw new ContextProcessingException("wrong credentials");
        }
        
        return null;
    }
    
}
