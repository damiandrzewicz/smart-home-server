/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.bootstrap;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.moderndev.smarthome.data.services.node.NodeService;
import com.moderndev.smarthome.data.services.node.NodeTypeService;

/**
 *
 * @author damian
 */
@Component
@Profile("dev")
@Slf4j
public class DataLoader implements CommandLineRunner{

    
    @Override
    public void run(String... args) throws Exception {
        
    }
    
}
