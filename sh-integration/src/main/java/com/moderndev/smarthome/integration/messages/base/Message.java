/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.messages.base;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author damian
 */
@Slf4j
public abstract class Message<T> {
    
    private static final String MessageOk = "ok";
    private static final String MessageError = "error";
    
    /**
     * Result Inner Class
     */
    public class Result{
  
        private ResultState state;
        private String message;
        T data;
        
        public void setOk(){
            this.state = ResultState.Ok;
        }
        
        public void setError(){
            this.state = ResultState.Error;
        }
        
        public void setError(String message){
            setError();
            this.message = message;
        }
        
        public boolean isOk(){
            return state.equals(ResultState.Ok);
        }
        
        public String getMessage(){
            return this.message;
        }
        
        public T getData(){
            return this.data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }
    
    //Abstract operations
    abstract public  Result parse(String message) throws JSONException;
    
    abstract public String get(final T entity) throws JSONException;
    
    public String getOk() throws JSONException{
       JSONObject root = new JSONObject();
       root.put("result", buildBaseResultJsonOk());
       return root.toString();
    }
    
    public String getError(String message) throws JSONException{
        JSONObject root = new JSONObject();
        root.put("result", buildBaseResultJsonError(message));
        return root.toString();
    }
    
    //Hidden operations
    protected JSONObject buildBaseResultJsonOk() throws JSONException {
        return buildResultJson(ResultState.Ok);
    }
    
    protected JSONObject buildBaseResultJsonError(String message) throws JSONException {
        JSONObject resultJson = buildResultJson(ResultState.Error);
        resultJson.put("message", message);
        return resultJson;
    }
    
    //Private operations
    private JSONObject buildResultJson(ResultState state) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("state", state.toString());
        return jsonObject;
    }
}
