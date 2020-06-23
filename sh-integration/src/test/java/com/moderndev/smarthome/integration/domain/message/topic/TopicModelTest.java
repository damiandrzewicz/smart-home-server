/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.message.topic;

import com.moderndev.smarthome.integration.message.MessageDirection;
import com.moderndev.smarthome.integration.domain.message.topic.TopicModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author damian
 */
public class TopicModelTest {
    
    TopicModel tm;
    
    public TopicModelTest() {
    }
    
    @BeforeEach
    public void setUp() {
        tm = new TopicModel();
    }

    @Test
    public void testGetDomain() {
        tm.setDomain("domain");
        assertEquals("domain", tm.getDomain());
    }

    @Test
    public void testGetReceiverId() {
        tm.setDomain("receiver");
        assertEquals("receiver", tm.getDomain());
    }

    @Test
    public void testGetSenderId() {
        tm.setDomain("sender");
        assertEquals("sender", tm.getDomain());
    }

    @Test
    public void testGetDelim() {
        assertEquals("/", tm.getDelim());
    }

    @Test
    public void testGetDirectionRequest() {
        tm.setDirection(MessageDirection.Request);
        assertEquals(MessageDirection.Request.getString(), tm.getDirection().getString());
    }
    
    @Test
    public void testGetDirectionResponse() {
        tm.setDirection(MessageDirection.Response);
        assertEquals(MessageDirection.Response.getString(), tm.getDirection().getString());
    }

    @Test
    public void testSetNullDomain() {
        assertThrows(NullPointerException.class, () -> {
            tm.setDomain(null);
        });
    }

    @Test
    public void testSetNullReceiverId() {
        assertThrows(NullPointerException.class, () -> {
            tm.setReceiverId(null);
        });
    }

    @Test
    public void testSetNullSenderId() {
        assertThrows(NullPointerException.class, () -> {
            tm.setSenderId(null);
        });
    }

    @Test
    public void testSetNullDelim() {
        assertThrows(NullPointerException.class, () -> {
            tm.setDelim(null);
        });
    }

    @Test
    public void testSetNullDirection() {
        assertThrows(NullPointerException.class, () -> {
            tm.setDirection(null);
        });
    }
    
}
