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
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import com.moderndev.smarthome.integration.domain.mqtt.MqttMessageModel;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
/**
 *
 * @author damian
 */

public class RequestTest {
    
    @Mock
    Request request;
    
    public RequestTest() {
    }
    
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testProcessWithoutResponseContext() throws Exception {
        
        Mockito.when(request.processContext(any()))
                .thenReturn(null);
        
        Mockito.when(request.process(any())).thenCallRealMethod();

        request.setQos(2);

        MqttMessageModel requestModel = new MqttMessageModel();
        requestModel.setTopic("myhome/id1/id2");
        requestModel.setPayload("{\"operation\":\"MyRequest\"}");
        requestModel.setQos(2);
        
        MqttMessageModel responseModel = request.process(requestModel);
        
        assertEquals("myhome/id2/id1", responseModel.getTopic());
        assertEquals("{\"operation\":\"MyResponse\",\"result\":{\"state\":\"ok\"}", responseModel.getPayload());
        assertEquals(1, responseModel.getQos());
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
