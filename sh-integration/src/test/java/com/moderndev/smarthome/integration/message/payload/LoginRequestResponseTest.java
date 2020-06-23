/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message.payload;

import com.moderndev.smarthome.integration.domain.message.LoginContextModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author damian
 */
public class LoginRequestResponseTest {
    
    LoginRequestResponse loginRequestResponse;
    
    public LoginRequestResponseTest() {
    }
    
    @BeforeEach
    public void setUp() {
        loginRequestResponse = new LoginRequestResponse();
    }

    @Test
    public void testBuildRequest() throws Exception {
    }

    @Test
    public void testParseRequest() throws Exception {
        String payload = "{\"operation\":\"testOperation\",\"login\":\"mylogin\",\"password\":\"mypwd\"}";
        LoginContextModel model = loginRequestResponse.parseRequest(payload);
        assertEquals("testOperation", model.getOperation());
        assertEquals("mylogin", model.getLogin());
        assertEquals("mypwd", model.getPassword());
    }

    @Test
    public void testBuildResponse() throws Exception {
    }

    @Test
    public void testParseResponse() throws Exception {
    }
    
}
