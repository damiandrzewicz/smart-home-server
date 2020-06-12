///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.moderndev.smarthome.integration.services.queues;
//
//import com.moderndev.smarthome.data.domain.smartnode.SmartNode;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Set;
//import javax.annotation.PostConstruct;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import com.moderndev.smarthome.data.services.NodeService;
//
///**
// *
// * @author damian
// */
//@Slf4j
//@Service
//public class SmartNodeQueue {
//    
//    private Set<SmartNode> nodes = Collections.synchronizedSet(new HashSet<SmartNode>());
//    
//    private NodeService nodeService;
//
//    public SmartNodeQueue(NodeService nodeService) {
//        this.nodeService = nodeService;
//    }
//    
//    @PostConstruct
//    public void init(){
//        this.nodeService.findAll().stream().forEach(n -> this.nodes.add(n));
//        log.debug("loaded {} nodes", this.nodes.size());
//    }
//    
//    public Set<String> loggedIds(){
//        
//    }
//    
//    public Set<String> registeredIds(){
//        
//    }
//    
//    public Set<String> activeIds(){
//        
//    }
//    
//
//}
