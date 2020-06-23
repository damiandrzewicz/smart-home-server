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
public class TopicParserEspTest {
    
    TopicParser tp;
    
    public TopicParserEspTest() {
    }
    
    @BeforeEach
    public void setUp() {
        tp = new TopicParserMqtt();
    }

    @Test
    public void testParseRequest() throws TopicParseException {
        MessageDirection dir = MessageDirection.Request;
        String request = "domain/id1/" + dir.getString() + "/id2";
        TopicModel model = tp.parse(request);
        assertEquals("domain", model.getDomain());
        assertEquals("id1", model.getReceiverId());
        assertEquals(dir.getString(), model.getDirection().getString());
        assertEquals("id2", model.getSenderId());
    }
    
    @Test
    public void testParseResponse() throws TopicParseException {
        MessageDirection dir = MessageDirection.Response;
        String response = "domain/id1/" + dir.getString() + "/id2";
        TopicModel model = tp.parse(response);
        assertEquals("domain", model.getDomain());
        assertEquals("id1", model.getReceiverId());
        assertEquals(dir.getString(), model.getDirection().getString());
        assertEquals("id2", model.getSenderId());
    }
    
    @Test
    public void testParseNullString() {
        assertThrows(NullPointerException.class, () -> {
            tp.parse(null);
        });
    }
    
    @Test
    public void testParseRequestThrowsException() {
        assertThrows(TopicParseException.class, ()->{
            tp.parse("test");
        });
    }
    
    @Test
    public void testParseEmptyRequest() {
        assertThrows(TopicParseException.class, ()->{
            tp.parse("");
        });
    }
}
