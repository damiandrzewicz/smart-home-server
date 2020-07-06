/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.utils;

import java.util.Set;
import javax.validation.ConstraintViolation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author damian
 */
@Slf4j
@Component
@Scope(value = "prototype")
public class ValidatorHelper {
    
    public <T> String checkViolations(Set<ConstraintViolation<T>> violations){
        
        if(!violations.isEmpty()){
            return buildString(violations);
        } else {
            return null;
        }
    }
    
    public <T> String buildString(Set<ConstraintViolation<T>> violations){
        
        String errors = new String();
        for(var violation : violations){
            errors += String.format("[%s]", violation.getMessage());
        }
        
        return errors;
    }
    
    public static String formatNullPropertyMsg(String propertyName){
        return String.format("property '%s' cannot be null", propertyName);
    }
    
    public static String formatBlankPropertyMsg(String propertyName){
        return String.format("property '%s' cannot be blank", propertyName);
    }
    
    public static String formatLowerThanPropertyMsg(String propertyName, int value){
        return String.format("property '%s' cannot be lower than %d", propertyName, value);
    }
    
    public static String formatHigherThanPropertyMsg(String propertyName, int value){
        return String.format("property '%s' cannot be higher than %d", propertyName, value);
    }
}

