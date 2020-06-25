/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.message;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author damian
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageResultModel{
    
    //@JsonFormat(shape = JsonFormat.Shape.OBJECT)
    public static enum State{
        Ok("ok"), Error("error");
        
        private String string;

        private State(String string) {
            this.string = string;
        }

        @JsonValue
        public String getString() {
            return string;
        }
    }

    private State state;
        
    private String message;

    @JsonIgnore
    public void setOk(){
        this.state = State.Ok;
    }
    
    @JsonIgnore
    public void setError(){
        this.state = State.Error;
    }
    
    @JsonIgnore
    public void setError(String message){
        setError();
        this.message = message;
    }
}
