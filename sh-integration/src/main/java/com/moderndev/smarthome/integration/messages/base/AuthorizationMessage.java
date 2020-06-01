/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.messages.base;

import com.moderndev.smarthome.integration.domain.base.Credentials;
import com.moderndev.smarthome.integration.domain.base.Credentials;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.messaging.MessageHeaders;

/**
 *
 * @author damian
 */
public class AuthorizationMessage extends Message<Credentials>
{
    @Override
    public Result parse(String message) throws JSONException {
        AuthorizationMessage.Result result = this.new Result();
        
        Credentials credentials = new Credentials();
        
        JSONObject jsonObject = new JSONObject(message);
        credentials.setLogin(jsonObject.getString("login"));
        credentials.setPassword(jsonObject.getString("password"));
        
        if(credentials.getLogin().isEmpty() && credentials.getPassword().isEmpty()){
            throw new JSONException("empty 'login' or ' password'");
        }
        
        result.setData(credentials);
        
        return result;
    }

    @Override
    public String get(Credentials entity) throws JSONException  {
        return "";
    }
        
}
