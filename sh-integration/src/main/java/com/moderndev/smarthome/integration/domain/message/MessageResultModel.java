/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.message;

/**
 *
 * @author damian
 */
public class MessageResultModel{
    
    public static enum State{
        Ok, Error
    }

    private State state;
        
    private String message;

    public void setOk(){
        this.state = State.Ok;
    }
    
    public void setError(){
        this.state = State.Error;
    }
    
    public void setError(String message){
        setError();
        this.message = message;
    }
}
