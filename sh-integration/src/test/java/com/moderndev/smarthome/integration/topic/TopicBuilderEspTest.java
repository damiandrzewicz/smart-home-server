/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.topic;

import com.moderndev.smarthome.integration.domain.topic.TopicDirection;
import com.moderndev.smarthome.integration.domain.topic.TopicModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author damian
 */
public class TopicBuilderEspTest {
    
    TopicBuilder tb;
    
    public TopicBuilderEspTest() {
    }
    
    @BeforeEach
    public void setUp() {
        tb = new TopicBuilderEsp();
    }

    @Test
    public void testBuildFullRequest() {
        String built = tb.build(TopicModel.builder()
                .domain("domain")
                .receiverId("id1")
                .direction(TopicDirection.Request)
                .senderId("id2")
                .build());
        
        assertEquals("domain/id1/" + TopicDirection.Request.getString() + "/id2", built);
    }
    
    @Test
    public void testBuildFullResponse() {
        String built = tb.build(TopicModel.builder()
                .domain("domain")
                .receiverId("id2")
                .direction(TopicDirection.Response)
                .senderId("id1")
                .build());
        
        assertEquals("domain/id2/" + TopicDirection.Response.getString() + "/id1", built);
    }
    
    @Test
    public void testBuildFromNullModel() {
        assertThrows(NullPointerException.class, () -> {
            tb.build(null);
        });
    }
    
}
