/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.services.managers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.moderndev.smarthome.data.domain.smartnode.SmartNode;
import com.moderndev.smarthome.data.domain.smartnode.SmartNodeIdentity;
import com.moderndev.smarthome.data.domain.smartnode.SmartNodeType;
import com.moderndev.smarthome.data.repository.SmartNodeRepository;
import com.moderndev.smarthome.data.services.SmartNodeService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 *
 * @author damian
 */
@ExtendWith(MockitoExtension.class)
public class SmartNodeManagerTest {

    @Mock
    SmartNodeService smartNodeService;
    
    @InjectMocks
    SmartNodeManager smartNodeManager;
    
    SmartNode smartNode;
    
    @BeforeEach
    void setUp() {
        this.smartNode = new SmartNode("mySystemId");
        
        SmartNodeType smartNodeType = new SmartNodeType("myNodeType");
        this.smartNode.setSmartNodeType(smartNodeType);
        
        SmartNodeIdentity smartNodeIdentity = new SmartNodeIdentity("mySmartNodeName");
        this.smartNode.setSmartNodeIdentity(smartNodeIdentity);
    }
    
    @Test
    void testZeroSmartNodesLogged(){
        assertEquals(0, this.smartNodeManager.loggedSmartNodesSize());
    }
    
    @Test
    void testZeroSmarrNodesIdentified(){
        assertEquals(0, this.smartNodeManager.identifiedSmartNodesSize());
    }
    
    @Test
    void testCheckSmartNodeLogged(){
        assertFalse(smartNodeManager.isSmartNodeLoggeed(this.smartNode));
    }
    
    @Test
    void testLoginSmartNode(){
        assertTrue(this.smartNodeManager.loginSmartNode(this.smartNode));
    }
  
}
