/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.messages.base;

import com.moderndev.smarthome.integration.domain.message.IntegrationMessage;
import com.moderndev.smarthome.integration.domain.message.IntegrationResultJsonObject;
import com.moderndev.smarthome.integration.domain.message.IntegrationMessageParseException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.reflect.Whitebox;

/**
 *
 * @author damian
 */
public class AuthorizationMessageTest {
    
    MessageImpl message = null;
    AuthorizationMessage authorizationMessage;
    
    static final String login = "testLogin";
    static final String password = "testPassword";

    static final  String loginField = "login";
    static final  String passwordField = "password";
    
    public AuthorizationMessageTest() {
    }
    
    @BeforeEach
    public void setUp() {
        this.message = new MessageImpl();
        this.authorizationMessage = PowerMockito.mock(AuthorizationMessage.class);
        
        Whitebox.setInternalState(this.authorizationMessage, loginField, login);
        Whitebox.setInternalState(this.authorizationMessage, passwordField, password);
    }

    @Test
    public void testParse(){
        var root = new JSONObject();
        root.put(loginField, login);
        root.put(passwordField, password);
        
        String request = root.toString();
        
        try{
            PowerMockito.doCallRealMethod()
                .when(this.authorizationMessage)
                .parse(request);
            this.authorizationMessage.parse(request);
        }catch(IntegrationMessageParseException ex){
            fail("no exception expected");
        }
    }
    
    @Test
    public void testParseThrowSyntaxError(){
        assertThrows(IntegrationMessageParseException.class, () -> {
            String request = "";
            PowerMockito.doCallRealMethod()
                .when(this.authorizationMessage)
                .parse(request);
            this.authorizationMessage.parse(request);
        });
    }

    @Test
    public void testGetResponse() {
    }
    
    public class MessageImpl extends IntegrationMessage{

        @Override
        public void parse(String message) {
        }

        @Override
        public String getResponse() {
            return null;
        }
        
        public JSONObject getRootObject(){
            return super.getRootObject();
        }
        
        public IntegrationResultJsonObject getIntegrationesultJsonObject(){
            return super.getIntegrationResultJsonObject();
        }
    }
    
}
