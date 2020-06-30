/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message;

import java.util.Set;
import javax.validation.ConstraintViolation;

/**
 *
 * @author damian
 */
public class ViolationStringBuilder {
    
    public static <T> String buildString(Set<ConstraintViolation<T>> violations){
        
        String errors = new String();
        
        for(var violation : violations){
            errors += String.format("[%s]", violation.getMessage());
        }
        
        return errors;
    }
}
