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
public class TopicBuilderEspTest {
    
    TopicBuilder tb;
    
    public TopicBuilderEspTest() {
    }
    
    @BeforeEach
    public void setUp() {
        tb = new TopicBuilderMqtt();
    }

    @Test
    public void testBuildFullRequest() {
        String built = tb.build(TopicModel.builder()
                .domain("domain")
                .receiverId("id1")
                .direction(MessageDirection.Request)
                .senderId("id2")
                .build());
        
        assertEquals("domain/id1/" + MessageDirection.Request.getString() + "/id2", built);
    }
    
    @Test
    public void testBuildFullResponse() {
        String built = tb.build(TopicModel.builder()
                .domain("domain")
                .receiverId("id2")
                .direction(MessageDirection.Response)
                .senderId("id1")
                .build());
        
        assertEquals("domain/id2/" + MessageDirection.Response.getString() + "/id1", built);
    }
    
    @Test
    public void testBuildFromNullModel() {
        assertThrows(NullPointerException.class, () -> {
            tb.build(null);
        });
    }
    
}
