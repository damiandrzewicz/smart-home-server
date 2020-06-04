/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.node;

/**
 *
 * @author damian
 */
public enum NodeType {
    WeatherStationSensor("weatherStationSensor"),
    LedDriver("ledDriver"),
    GasSensor("gasSensor"),
    BlindDriver("blindDriver"),
    OpenableSensor("openableSensor");
    
    private String string;
    
    private NodeType(String nodeType){
        this.string = nodeType;
    }
    
    public String toString(){
        return this.string;
    }
}
