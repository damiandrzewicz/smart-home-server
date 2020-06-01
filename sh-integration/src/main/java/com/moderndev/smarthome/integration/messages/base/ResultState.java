/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.messages.base;

/**
 *
 * @author damian
 */
public enum ResultState {
    
    Ok("ok"),
    Error("error");
    
    private String value;
    
    ResultState(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
