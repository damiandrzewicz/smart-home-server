/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.message;

import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author damian
 */
public class IntegrationResultJsonObject {
    
    public static final String MessageOk = "ok";
    public static final String MessageError = "error";
    
    private JSONObject root = null;
    private JSONObject resultObject  = null;
    
    public IntegrationResultJsonObject(JSONObject root) throws JSONException{
        this.root = root;
        this.resultObject = addResultObjectToRoot();
    }
    
    public void setStateOk(){
        setState(MessageOk);
    }
    
    public void setStateError(String message){
        setState(MessageError);
        setMessage(message);
    }
    
    private JSONObject addResultObjectToRoot() throws JSONException{
        var resultJsonObject = new JSONObject();
        root.put("result", resultJsonObject);
        return resultJsonObject;
    }
    
    private void setState(String message){
        this.resultObject.put("state", message);
    }
    
    private void setMessage(String message){
        this.resultObject.put("message", message);
    }
}
