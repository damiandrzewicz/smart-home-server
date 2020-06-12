///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.moderndev.smarthome.integration.services.managers.smartnode;
//
//import com.moderndev.smarthome.data.domain.smartnode.SmartNode;
//import com.moderndev.smarthome.integration.services.utils.BeanUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import com.moderndev.smarthome.data.services.NodeService;
//
///**
// *
// * @author damian
// */
//@Slf4j
//public class SmartNodeSupervisor {
// 
//    private NodeService nodeService;
//    private SmartNodeManager smartNodeManager;
//    
//    private SmartNode smartNode;
//
//    public SmartNodeSupervisor(String clientId) {
//        this.nodeService = BeanUtil.getBean(NodeService.class);
//        this.smartNodeManager = BeanUtil.getBean(SmartNodeManager.class);
//        SmartNode foundNode = this.smartNodeManager.findById(id);
//        if(foundNode != null){
//            this.smartNode = foundNode;
//        } else {
//            this.smartNode = new SmartNode(clientId);
//        }
//    }
//    
//    public void login(){
//        
//    }
//    
//    public void logout(){
//        
//    }
//    
//    public boolean isLogged(){
//        
//    }
//    
//    public void updateMetadata(SmartNodeMetadata smartNodeMetadata){
//        
//    }
//    
//    public SmartNodeMetadata getMetadata(){
//        
//    }
//    
//    public void updateRegistration(SmartNodeRegisterMetadata smartNodeRegisterMetadata){
//        //TODO set custom name for node
//        
//    }
//    
//    public void unregister(){
//        
//    }
//    
//    public boolean isRegistered(){
//        
//    }
//    
//    private void activate(String id){
//        
//    }
//    
//    public boolean isActive(String id){
//        
//    }
//    
//    
//}
