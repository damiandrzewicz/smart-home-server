/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.moderndev.smarthome.integration.message.ContextProcessingException;
import com.moderndev.smarthome.integration.message.MessageFactory;
import com.moderndev.smarthome.integration.message.Request;
import com.moderndev.smarthome.integration.utils.ValidatorHelper;
import javax.validation.Validator;
import org.springframework.stereotype.Component;

/**
 *
 * @author damian
 */
@Component
public class DeviceInfoRequest extends Request{

    public DeviceInfoRequest(ObjectMapper objectMapper, ValidatorHelper validatorHelper) {
        super(objectMapper, validatorHelper);
    }


    @Override
    protected JsonNode processContext(String receiverId, JsonNode context) throws ContextProcessingException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
