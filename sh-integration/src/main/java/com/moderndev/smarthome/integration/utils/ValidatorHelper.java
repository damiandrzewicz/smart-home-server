/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.utils;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import lombok.Getter;
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
@Getter
public class ValidatorHelper {
    
    private Validator validator;

    public ValidatorHelper(Validator validator) {
        this.validator = validator;
    }
    
    public <T> String checkViolations(T object){
        
        Set<ConstraintViolation<T>> violations = getValidator().validate(object);
        
        if(!violations.isEmpty()){
            return buildString(violations);
        } else {
            return null;
        }
    }
    
    private <T> String buildString(Set<ConstraintViolation<T>> violations){
        
        String errors = new String();
        for(var violation : violations){
            errors += String.format("[%s]",
                    String.format("%s %s", violation.getPropertyPath(), violation.getMessage()));
        }
        
        return errors;
    }
    
}

