/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author damian
 */
public class RequestTest {
    
    public RequestTest() {
    }
    
    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testProcess() throws Exception {
    }

    @Test
    public void testProcessContext() throws Exception {
    }

    @Test
    public void testGetObjectMapper() {
    }

    @Test
    public void testGetQos() {
    }

    @Test
    public void testSetObjectMapper() {
    }

    @Test
    public void testSetQos() {
    }

    public class RequestImpl extends Request {

        public RequestImpl() {
            super(null);
        }

        public JsonNode processContext(JsonNode context) throws ContextProcessingException {
            return null;
        }
    }
    
}
