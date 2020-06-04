/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.message;

import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author damian
 */
public abstract class IntegrationMessage implements IntegrationRequest, IntegrationResponse{
   
    private JSONObject root = null;
    private IntegrationResultJsonObject integrationResultJsonObject = null;
    
    protected final String ParseJsonErrorText = "ParseJsonError";

    public IntegrationMessage() throws JSONException {
        this.root = new JSONObject();
        this.integrationResultJsonObject = new IntegrationResultJsonObject(this.root);
    }
    
    protected JSONObject getRootObject(){
        return this.root;
    }
    
    protected IntegrationResultJsonObject getIntegrationResultJsonObject() {
        return this.integrationResultJsonObject;
    }
}
