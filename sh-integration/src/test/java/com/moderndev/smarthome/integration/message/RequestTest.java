/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message;

import com.fasterxml.jackson.core.JsonProcessingException;
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
import com.moderndev.smarthome.integration.domain.mqtt.MqttMessageModel;
import com.moderndev.smarthome.integration.utils.ValidatorHelper;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Validation;
import javax.validation.Validator;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Answers;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import org.springframework.test.context.junit.jupiter.SpringExtension;
/**
 *
 * @author damian
 */
public class RequestTest {
    
    private static Validator validator;
    
    Request request;
    
    int qos = 2;
    
    public RequestTest() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
    
    @BeforeEach
    public void setUp() throws MessgeProcessingException {
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        
        ValidatorHelper validatorHelper = 
                new ValidatorHelper(validator);
        
        request = Mockito.mock(Request.class, Mockito.withSettings().useConstructor(objectMapper, validatorHelper));

        Mockito.when(request.getValidatorHelper()).thenCallRealMethod();
        Mockito.when(request.getObjectMapper()).thenCallRealMethod();
        Mockito.when(request.process(any())).thenCallRealMethod();
        Mockito.when(request.getResponseQos()).thenReturn(qos);
        Mockito.when(request.getMessageName()).thenReturn("myRequest");
    }
    
    @Test
    void testProcessNullMqttMessageModel() throws MessgeProcessingException{
        
        assertThrows(IllegalArgumentException.class, ()->{request.process(null);});
    }
    
    @Test
    void testProcessBlankMqttMessageModel(){
        
        assertThrows(MessgeProcessingException.class, ()->{ request.process(new MqttMessageModel()); });
    }
    
    @Test
    void testProcessEmptyContentAndReturnNullContent() throws ContextProcessingException, MessgeProcessingException{
        Mockito.when(request.processContext(any(), any())).thenReturn(null);
        
        String topic = "myhome/id1/id2";
        String payload = "{\"messageName\":\"myRequest\"}";
        
        MqttMessageModel modelOut = request.process(new MqttMessageModel(topic, payload, qos));
        assertEquals("myhome/id2/id1", modelOut.getTopic());
        assertEquals("{\"messageName\":\"myResponse\",\"result\":{\"state\":\"ok\"}}", modelOut.getPayload());
        assertEquals(this.qos, modelOut.getQos());
    }
    
    @Test
    void testProcessReturnFilledContent() throws ContextProcessingException, JsonProcessingException, MessgeProcessingException{
        
        JSONObject content = new JSONObject();
        content.appendField("testField", "testValue");
        
        ObjectMapper objectMapper = request.getObjectMapper();
        Mockito.when(request.processContext(any(), any())).thenReturn(objectMapper.readTree(content.toString()));
        
        String topic = "myhome/id1/id2";
        String payload = "{\"messageName\":\"myRequest\"}";
        
        MqttMessageModel modelOut = request.process(new MqttMessageModel(topic, payload, qos));
        assertEquals("myhome/id2/id1", modelOut.getTopic());
        assertEquals("{\"messageName\":\"myResponse\","
                + "\"result\":{\"state\":\"ok\"},"
                + "\"content\":{\"testField\":\"testValue\"}}", modelOut.getPayload());
        assertEquals(this.qos, modelOut.getQos());
    }
    
    //----------------------------------------------

//    @Test
//    public void testProcessMessageResponseNoContext() throws Exception {
//        
//        Mockito.when(request.processContext(any(), any())).thenReturn(null);
//        
//        MqttMessageModel requestModel = new MqttMessageModel();
//        requestModel.setTopic("myhome/id1/id2");
//        requestModel.setPayload("{\"operation\":\"MyRequest\"}");
//        requestModel.setQos(2);
//        
//        MqttMessageModel responseModel = request.process(requestModel);
//        
//        assertEquals("myhome/id2/id1", responseModel.getTopic());
//        assertEquals("{\"operation\":\"MyResponse\",\"result\":{\"state\":\"ok\"}}", responseModel.getPayload());
//        assertEquals(qos, responseModel.getQos());
//    }
//    
//    @Test
//    void testProcessNullModel(){
//        assertThrows(MessgeProcessingException.class, ()->{request.process(null);}) ;
//    }
//    
//    @Test
//    void testProcessMessageNullTopic(){
//        MqttMessageModel requestModel = new MqttMessageModel();
//        assertThrows(MessgeProcessingException.class, ()->{
//            request.process(requestModel);
//        });
//    }
//    
//    @Test
//    void testProcessMessageWrongTopic(){
//        MqttMessageModel requestModel = new MqttMessageModel();
//        requestModel.setTopic("myhome/asd/123/234");
//        assertThrows(MessgeProcessingException.class, ()->{
//            request.process(requestModel);
//        });
//    }
//    
//    @Test
//    void testProcessMessageNullPayload(){
//        MqttMessageModel requestModel = new MqttMessageModel();
//        requestModel.setTopic("myhome/123/234");
//        assertThrows(MessgeProcessingException.class, ()->{
//            request.process(requestModel);
//        });
//    }
//    
//    @Test
//    void testProcessMessageWrongFormattedPayload() throws ContextProcessingException, JsonProcessingException{
//        Mockito.when(request.processContext(any(), any())).thenReturn(null);
//        
//        MqttMessageModel requestModel = new MqttMessageModel();
//        requestModel.setTopic("myhome/id1/id2");
//        requestModel.setPayload("{\"operation\":\"MyRequest\"");
//        requestModel.setQos(2);
//        
//        assertThrows(MessgeProcessingException.class, ()->{
//            request.process(requestModel);
//        });
//    }
//    
//    @Test
//    void testProcessMessageResponseWithWrongContext() throws ContextProcessingException, MessgeProcessingException, JsonProcessingException{
//        
//        ObjectNode context = JsonNodeFactory.instance.objectNode();
//        context.put("field1", "test1");
//        context.put("key", 1.1);
//        
//        Mockito.when(request.processContext(any(), any())).thenReturn((JsonNode)context);
//        
//        MqttMessageModel requestModel = new MqttMessageModel();
//        requestModel.setTopic("myhome/id1/id2");
//        requestModel.setPayload("{\"operation\":\"MyRequest\"");
//        requestModel.setQos(2);
//        
//        assertThrows(MessgeProcessingException.class, ()->{
//            request.process(requestModel);
//        });
//    }
//    
//    @Test
//    void testProcessMessageResultErrorWithMsg() throws ContextProcessingException, MessgeProcessingException, JsonProcessingException{
//        
//        Mockito.when(request.processContext(any(), any())).thenThrow(ContextProcessingException.class);
//        
//        MqttMessageModel requestModel = new MqttMessageModel();
//        requestModel.setTopic("myhome/id1/id2");
//        requestModel.setPayload("{\"operation\":\"MyRequest\"}");
//        requestModel.setQos(2);
//        
//        MqttMessageModel responseModel = request.process(requestModel);
//        
//        assertEquals("{\"operation\":\"MyResponse\","
//                + "\"result\":{\"state\":\"error\",\"message\":\"ContextProcessingException\"}}", responseModel.getPayload());
//    }
//    
//
//    @Test
//    public void testProcessContext() throws Exception {
//    }
//
//    @Test
//    public void testGetObjectMapper() {
//    }
//
//    @Test
//    public void testGetQos() {
//    }
//
//    @Test
//    public void testSetObjectMapper() {
//    }
//
//    @Test
//    public void testSetQos() {
//    }
}
