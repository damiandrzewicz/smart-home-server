/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mockito;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import com.moderndev.smarthome.integration.domain.mqtt.MqttMessageModel;
import com.moderndev.smarthome.integration.message.topic.TopicProcessingException;
import org.junit.jupiter.api.Disabled;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
/**
 *
 * @author damian
 */
@Disabled
public class RequestTest {
    
    Request request;
    
    int qos = 2;
    
    public RequestTest() {
    }
    
    @BeforeEach
    public void setUp() throws MessgeProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        
        MessageFactory messageFactory = new MessageFactory();
        request = Mockito.mock(Request.class, Mockito.withSettings().useConstructor(objectMapper, messageFactory));
        
        Mockito.when(request.process(any())).thenCallRealMethod();
        Mockito.when(request.getResponseQos()).thenReturn(qos);
        Mockito.when(request.getMessageName()).thenReturn("mymessage");
    }

    @Test
    public void testProcessMessageResponseNoContext() throws Exception {
        
        Mockito.when(request.processContext(any())).thenReturn(null);
        
        MqttMessageModel requestModel = new MqttMessageModel();
        requestModel.setTopic("myhome/id1/id2");
        requestModel.setPayload("{\"operation\":\"MyRequest\"}");
        requestModel.setQos(2);
        
        MqttMessageModel responseModel = request.process(requestModel);
        
        assertEquals("myhome/id2/id1", responseModel.getTopic());
        assertEquals("{\"operation\":\"MyResponse\",\"result\":{\"state\":\"ok\"}}", responseModel.getPayload());
        assertEquals(qos, responseModel.getQos());
    }
    
    @Test
    void testProcessNullModel(){
        assertThrows(MessgeProcessingException.class, ()->{request.process(null);}) ;
    }
    
    @Test
    void testProcessMessageNullTopic(){
        MqttMessageModel requestModel = new MqttMessageModel();
        assertThrows(MessgeProcessingException.class, ()->{
            request.process(requestModel);
        });
    }
    
    @Test
    void testProcessMessageWrongTopic(){
        MqttMessageModel requestModel = new MqttMessageModel();
        requestModel.setTopic("myhome/asd/123/234");
        assertThrows(MessgeProcessingException.class, ()->{
            request.process(requestModel);
        });
    }
    
    @Test
    void testProcessMessageNullPayload(){
        MqttMessageModel requestModel = new MqttMessageModel();
        requestModel.setTopic("myhome/123/234");
        assertThrows(MessgeProcessingException.class, ()->{
            request.process(requestModel);
        });
    }
    
    @Test
    void testProcessMessageWrongFormattedPayload() throws ContextProcessingException{
        Mockito.when(request.processContext(any())).thenReturn(null);
        
        MqttMessageModel requestModel = new MqttMessageModel();
        requestModel.setTopic("myhome/id1/id2");
        requestModel.setPayload("{\"operation\":\"MyRequest\"");
        requestModel.setQos(2);
        
        assertThrows(MessgeProcessingException.class, ()->{
            request.process(requestModel);
        });
    }
    
    @Test
    void testProcessMessageResponseWithWrongContext() throws ContextProcessingException, MessgeProcessingException{
        
        ObjectNode context = JsonNodeFactory.instance.objectNode();
        context.put("field1", "test1");
        context.put("key", 1.1);
        
        Mockito.when(request.processContext(any())).thenReturn((JsonNode)context);
        
        MqttMessageModel requestModel = new MqttMessageModel();
        requestModel.setTopic("myhome/id1/id2");
        requestModel.setPayload("{\"operation\":\"MyRequest\"");
        requestModel.setQos(2);
        
        assertThrows(MessgeProcessingException.class, ()->{
            request.process(requestModel);
        });
    }
    
    @Test
    void testProcessMessageResultErrorWithMsg() throws ContextProcessingException, MessgeProcessingException{
        
        Mockito.when(request.processContext(any())).thenThrow(ContextProcessingException.class);
        
        MqttMessageModel requestModel = new MqttMessageModel();
        requestModel.setTopic("myhome/id1/id2");
        requestModel.setPayload("{\"operation\":\"MyRequest\"}");
        requestModel.setQos(2);
        
        MqttMessageModel responseModel = request.process(requestModel);
        
        assertEquals("{\"operation\":\"MyResponse\","
                + "\"result\":{\"state\":\"error\",\"message\":\"ContextProcessingException\"}}", responseModel.getPayload());
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
}
