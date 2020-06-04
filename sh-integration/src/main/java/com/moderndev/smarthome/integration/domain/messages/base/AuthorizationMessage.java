/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.messages.base;

import com.moderndev.smarthome.integration.domain.message.IntegrationMessage;
import com.moderndev.smarthome.integration.domain.message.IntegrationMessageParseException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 * @author damian
 */
@Slf4j
public class AuthorizationMessage extends IntegrationMessage{
    
    @Value("${integration.node.login:testLogin}")
    private String login;
    
    @Value("${integration.node.password:testPassword}")
    private String password;
    
    private final String WrongCredentialsText = "WrongCredentials";
    private final String EmptyLoginText = "EmptyLogin";
    private final String EmptyPasswordText = "EmptyPassword";
    

    @Override
    public void parse(String message) throws IntegrationMessageParseException {
        try{
            var root = new JSONObject(message);
            var login = root.getString("login");
            var password = root.getString("password");
            
            //check values
            if(login != null && login.isEmpty()){
                log.error(EmptyLoginText);
                throw new IntegrationMessageParseException(EmptyLoginText);
            }else if(password != null && password.isEmpty()){
                log.error(EmptyPasswordText);
                throw new IntegrationMessageParseException(EmptyPasswordText);
            }
            
            //check authorization
            if(!login.equals(this.login) || password.equals(this.password)){
                log.warn(WrongCredentialsText);
                throw new IntegrationMessageParseException(WrongCredentialsText);
            }
            
        }catch(JSONException ex){
            log.error(ex.getMessage());
            throw new IntegrationMessageParseException(ParseJsonErrorText, ex);
        }
    }

    @Override
    public String getResponse() {
        return null;
    }
}
