/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message.topic;

import com.moderndev.smarthome.integration.message.MessageDirection;
import com.moderndev.smarthome.integration.domain.message.topic.TopicModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author damian
 */
public class TopicEspTest {
    
    Topic topic;
    
    public TopicEspTest() {
    }
    
    @BeforeEach
    public void setUp() {
        topic = new TopicEsp();
    }

    @Test
    public void testBuildFullRequest() throws TopicProcessingException {
        String built = topic.build(TopicModel.builder()
                .domain("domain")
                .receiverId("id1")
                .senderId("id2")
                .build());
        
        assertEquals("domain/id1/id2", built);
    }
    
    @Test
    public void testBuildFullResponse() throws TopicProcessingException {
        String built = topic.build(TopicModel.builder()
                .domain("domain")
                .receiverId("id2")
                .senderId("id1")
                .build());
        
        assertEquals("domain/id2/id1", built);
    }
    
    @Test
    public void testBuildFromNullModel() {
        assertThrows(NullPointerException.class, () -> {
            topic.build(null);
        });
    }
    
        @Test
    public void testParseRequest() throws TopicProcessingException {
        String request = "domain/id1/id2";
        TopicModel model = topic.parse(request);
        assertEquals("domain", model.getDomain());
        assertEquals("id1", model.getReceiverId());
        assertEquals("id2", model.getSenderId());
    }
    
    @Test
    public void testParseResponse() throws TopicProcessingException   {
        String response = "domain/id1/id2";
        TopicModel model = topic.parse(response);
        assertEquals("domain", model.getDomain());
        assertEquals("id1", model.getReceiverId());
        assertEquals("id2", model.getSenderId());
    }
    
    @Test
    public void testParseNullString() {
        assertThrows(NullPointerException.class, () -> {
            topic.parse(null);
        });
    }
    
    @Test
    public void testParseRequestThrowsException() {
        assertThrows(TopicProcessingException.class, ()->{
            topic.parse("test");
        });
    }
    
    @Test
    public void testParseEmptyRequest() {
        assertThrows(TopicProcessingException.class, ()->{
            topic.parse("");
        });
    }
}
