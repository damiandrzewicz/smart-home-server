///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.moderndev.smarthome.integration.services.managers;
//
//import com.moderndev.smarthome.integration.services.managers.smartnode.SmartNodeManagerException;
//import com.moderndev.smarthome.integration.services.managers.smartnode.SmartNodeManager;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import com.moderndev.smarthome.data.domain.smartnode.SmartNode;
//import com.moderndev.smarthome.data.domain.smartnode.SmartNodeIdentity;
//import com.moderndev.smarthome.data.domain.smartnode.SmartNodeType;
//import java.time.LocalDateTime;
//import java.time.temporal.ChronoUnit;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.mockito.Mockito.*;
//import static org.mockito.ArgumentMatchers.*;
//import com.moderndev.smarthome.data.repository.NodeRepository;
//import com.moderndev.smarthome.data.services.NodeService;
//
///**
// *
// * @author damian
// */
//@ExtendWith(MockitoExtension.class)
//public class SmartNodeManagerTest {
//
//    @Mock
//    NodeService smartNodeService;
//    
//    @InjectMocks
//    SmartNodeManager smartNodeManager;
//    
//    SmartNode smartNode;
//    
//    @BeforeEach
//    void setUp() {
//        this.smartNode = new SmartNode("mySystemId");
//        
//        SmartNodeType smartNodeType = new SmartNodeType("myNodeType");
//        this.smartNode.setSmartNodeType(smartNodeType);
//        
//        SmartNodeIdentity smartNodeIdentity = new SmartNodeIdentity("mySmartNodeName");
//        this.smartNode.setSmartNodeIdentity(smartNodeIdentity);
//    }
//    
//    @Test
//    void testZeroSmartNodesLogged(){
//        assertEquals(0, this.smartNodeManager.loggedSmartNodesSize());
//    }
//    
//    @Test
//    void testZeroSmartNodesIdentified(){
//        assertEquals(0, this.smartNodeManager.identifiedSmartNodesSize());
//    }
//    
//    @Test
//    void testIsSmartNodeLogged(){
//        try {
//            assertNotNull(this.smartNodeManager.isSmartNodeLoggeed(this.smartNode));
//            verify(this.smartNodeManager, times(1)).isSmartNodeLoggeed(any());
//        } catch (SmartNodeManagerException ex) {
//            fail(ex.getMessage());
//        }
//    }
//
//    @Test
//    void testLoginSmartNode(){
//        try {
//            SmartNode loggedNode = this.smartNodeManager.loginSmartNode(this.smartNode);
//            assertEquals(1, this.smartNodeManager.loggedSmartNodesSize());
//            SmartNode checkLoggedNode = this.smartNodeManager.isSmartNodeLoggeed(this.smartNode);
//            
//            verify(this.smartNodeManager, times(2)).isSmartNodeLoggeed(any());
//            verify(this.smartNodeManager, times(1)).activateSmartNode(any());
//            assertEquals(checkLoggedNode, loggedNode);
//        } catch (SmartNodeManagerException ex) {
//            fail(ex.getMessage());
//        }
//    }
//    
//    @Test
//    void testLogoutSmartNode(){
//        try {
//            SmartNode loggedNode = this.smartNodeManager.loginSmartNode(this.smartNode);
//            assertEquals(1, this.smartNodeManager.loggedSmartNodesSize());
//            assertTrue(this.smartNodeManager.logoutSmartNode(this.smartNode));
//            assertEquals(0, this.smartNodeManager.loggedSmartNodesSize());
//            assertNull(this.smartNodeManager.isSmartNodeLoggeed(this.smartNode));
//        } catch (SmartNodeManagerException ex) {
//            fail(ex.getMessage());
//        }
//
//    }
//    
//    @Test
//    void testIdentifySmartNode(){
//        try {
//            SmartNode loggedNode = this.smartNodeManager.loginSmartNode(this.smartNode);
//            SmartNode identifiedNode = this.smartNodeManager.identifySmartNode(loggedNode);
//            assertNotNull(identifiedNode);
//            
//            assertNotNull(identifiedNode.getSmartNodeIdentity());
//            assertNotNull(this.smartNodeManager.isSmartNodeIdentified(identifiedNode));
//            
//            verify(this.smartNodeManager, times(2)).isSmartNodeLoggeed(any());
//            verify(this.smartNodeService, times(1)).save(any());
//            
//        } catch (SmartNodeManagerException ex) {
//            fail(ex.getMessage());
//        }
//    }
//    
//        @Test
//    void testAlreadyIdentifiedSmartNode(){
//        try {
//            SmartNode loggedNode = this.smartNodeManager.loginSmartNode(this.smartNode);
//            SmartNode identifiedNode = this.smartNodeManager.identifySmartNode(loggedNode);
//            assertNotNull(identifiedNode);
//            
//            assertNotNull(this.smartNodeManager.isSmartNodeIdentified(identifiedNode));
//            assertNotNull(this.smartNodeManager.identifySmartNode(loggedNode));
//            
//            verify(this.smartNodeManager, times(3)).isSmartNodeLoggeed(any());
//            verify(this.smartNodeService, times(2)).save(any());
//            
//        } catch (SmartNodeManagerException ex) {
//            fail(ex.getMessage());
//        }
//    }
//    
//    @Test
//    void dropSmartNodeIdentification(){
//        try {
//            SmartNode loggedNode = this.smartNodeManager.loginSmartNode(this.smartNode);
//            SmartNode identifiedNode = this.smartNodeManager.identifySmartNode(loggedNode);
//            assertTrue(this.smartNodeManager.dropSmartNodeIdentification(identifiedNode));
//            
//            assertNull(identifiedNode.getSmartNodeIdentity());
//            verify(this.smartNodeService, times(1)).delete(any());
//            
//        } catch (SmartNodeManagerException ex) {
//            fail(ex.getMessage());
//        }
//    }
//    
//    @Test
//    void dropIdentityNotIdentifiedSmartNode(){
//        assertFalse(this.smartNodeManager.dropSmartNodeIdentification(this.smartNode));
//    }
//    
//    @Test
//    void testIdentityNotLoggedSmartNode(){
//        
//    }
//    
//    @Test
//    void testActivateSmartNodeDuringLogin(){
//        try {
//            assertNull(this.smartNode.getLatestActiveDateTime());
//            SmartNode loggedSmartNode = this.smartNodeManager.loginSmartNode(this.smartNode);
//            
//            verify(this.smartNodeService, times(1)).save(any());
//            assertTrue(loggedSmartNode.getLatestActiveDateTime().until(LocalDateTime.now(), ChronoUnit.SECONDS) < 60);
//        } catch (SmartNodeManagerException ex) {
//            fail(ex.getMessage());
//        }
//    }
//    
//        
//    @Test
//    void testActivateSmartNode(){
//        try {
//            this.smartNodeManager.loginSmartNode(this.smartNode);
//            SmartNode activatedSmartNode = this.smartNodeManager.activateSmartNode(this.smartNode);
//            
//            verify(this.smartNodeService, times(1)).save(any());
//            assertTrue(activatedSmartNode.getLatestActiveDateTime().until(LocalDateTime.now(), ChronoUnit.SECONDS) < 60); 
//        } catch (SmartNodeManagerException ex) {
//            fail(ex.getMessage());
//        }
//    }
//    
//    @Test
//    void testActivateNotLoggedSmartNode(){
//        assertNull(this.smartNodeManager.activateSmartNode(this.smartNode));
//        verify(this.smartNodeService, times(0)).save(any());
//    }
//    
//    @Test
//    void testDeactivateSmartNode(){
//        try {
//            this.smartNodeManager.loginSmartNode(this.smartNode);
//            SmartNode activatedSmartNode = this.smartNodeManager.activateSmartNode(this.smartNode);
//            
//            verify(this.smartNodeService, times(0)).save(any());
//            assertTrue(this.smartNodeManager.deactivateSmartNode(activatedSmartNode));
//        } catch (SmartNodeManagerException ex) {
//            fail(ex.getMessage());
//        }
//        
//    }
//    
//    @Test
//    void testDeactivateNotLoggedSmartNode(){
//        try {
//            SmartNode activatedSmartNode = this.smartNodeManager.activateSmartNode(this.smartNode);
//            
//            verify(this.smartNodeService, times(0)).save(any());
//            assertFalse(this.smartNodeManager.deactivateSmartNode(activatedSmartNode));
//        } catch (SmartNodeManagerException ex) {
//            fail(ex.getMessage());
//        }
//    }
//    
//    @Test
//    void testIsNodeActive(){
//        try {
//            SmartNode loggedSmartNode = this.smartNodeManager.loginSmartNode(this.smartNode);
//            assertTrue(this.smartNodeManager.isNodeActive(loggedSmartNode));
//        } catch (SmartNodeManagerException ex) {
//            fail(ex.getMessage());
//        }
//    }
//    
//    @Test
//    void testIsNotLoggedNodeActive(){
//        
//    }
//  
//}
