/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.message;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * @author damian
 */
//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum State {
    
    Ok("ok"), 
    Error("error");

    private String string;

    private State(String string) {
        this.string = string;
    }

    @JsonValue
    public String getString() {
        return string;
    }
}
