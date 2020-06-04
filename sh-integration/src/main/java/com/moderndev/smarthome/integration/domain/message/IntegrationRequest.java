/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.message;

/**
 *
 * @author damian
 */
@FunctionalInterface
public interface IntegrationRequest {
    
    void parse(String message) throws IntegrationMessageParseException;
}
