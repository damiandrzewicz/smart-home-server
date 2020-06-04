/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.message;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author damian
 */
public class ResultObjectBuilderTest {
    
    JSONObject root = null;
    IntegrationResultJsonObject integrationResultJsonObject;
    
    ResultObjectBuilderTest() {
    }
    
    @BeforeEach
    void setUp() {
        this.root = new JSONObject();
        this.integrationResultJsonObject = new IntegrationResultJsonObject(root);
    }
    
    @Test
    void testCreateResultObject(){
        this.integrationResultJsonObject.setStateOk();
        var resultObj = this.root.getJSONObject("result");
        assertNotNull(resultObj);
    }

    @Test
    void testSetStateOk() {
        this.integrationResultJsonObject.setStateOk();
        JSONObject resultObj = this.root.getJSONObject("result");
        String state = resultObj.getString("state");
        assertEquals(IntegrationResultJsonObject.MessageOk, state);
    }

    @Test
    void testSetStateError() {
        String testMessage = "errormsg";
        this.integrationResultJsonObject.setStateError(testMessage);
        JSONObject resultObj = this.root.getJSONObject("result");
        String state = resultObj.getString("state");
        String message = resultObj.getString("message");
        assertEquals(IntegrationResultJsonObject.MessageError, state);
        assertEquals(testMessage, message);
    }
    
    @Test
    void testThrowNullPointerException(){
        Assertions.assertThrows(NullPointerException.class, () -> { new IntegrationResultJsonObject(null);});
    }
    
}
