/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.message;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;

/**
 *
 * @author damian
 */
public class MessageTest {
    
    MessageImpl messageImpl = null;
    
    MessageTest() {
    }
    
    @BeforeEach
    void setUp() {
        this.messageImpl = new MessageImpl();
    }

    @Test
    void testGetRootObject() {
        assertNotNull(this.messageImpl.getRootObject());
    }

    @Test
    void testGetResultJsonObjectBuilder() {
        assertNotNull(this.messageImpl.getIntegrationesultJsonObject());
    }
    
    public class MessageImpl extends IntegrationMessage{

        @Override
        public void parse(String message) {
        }

        @Override
        public String getResponse() {
            return null;
        }
        
        public JSONObject getRootObject(){
            return super.getRootObject();
        }
        
        public IntegrationResultJsonObject getIntegrationesultJsonObject(){
            return super.getIntegrationResultJsonObject();
        }
    }
    
}
