/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.custom;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author damian
 */
public class JacksonTest {
    
    ObjectMapper om;
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Field1{
        public String field1;
    }
    
    //@JsonIgnoreProperties(ignoreUnknown = false)
    //
    static class Field2{
        public String field2;
        
        public String field3;

//        @JsonCreator
//        public Field2(@JsonProperty("field3") String field3) {
//            this.field3 = field3;
//        }
    }
    
    static class Field4{
        public String field5;
        public Field1 classfield;
    }
    
    public JacksonTest() {
    }
    
    @BeforeEach
    public void setUp() {
        om = new ObjectMapper();
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true);
    }
    
    
    //@Test
    void test() throws JsonProcessingException{
        String data = "{\"field1\":\"test1\",\"field2\":\"test2\"}";
        
        Field1 f1 = om.readValue(data, Field1.class);
        
        Field2 f2 = om.readValue(data, Field2.class);
        
        om.readvalue
        
        assertEquals("test1", f1.field1);
        assertEquals("test2", f2.field2);
    }
    
    @Test
    void test1() throws JsonProcessingException{
        String data = "{\"field5\":\"asd\",\"classfield\":{\"field1\":\"zxc\"}}";
        
        Field4 f4 = om.readValue(data, Field4.class);
        
        Field1 f1 = om.readValue(data, Field1.class);
        
        assertEquals("asd", f4.field5);
        assertEquals("zxc", f1.field1);
    }
    
}
