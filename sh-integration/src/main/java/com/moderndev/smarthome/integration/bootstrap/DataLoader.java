/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.bootstrap;

import com.moderndev.smarthome.database.services.NodeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 *
 * @author damian
 */
@Component
public class DataLoader implements CommandLineRunner{

    private final NodeService nodeService;

    public DataLoader(NodeService nodeService) {
        this.nodeService = nodeService;
    }
    
    
    
    @Override
    public void run(String... args) throws Exception {
        
    }
    
}
