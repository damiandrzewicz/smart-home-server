///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.moderndev.smarthome.integration.services.managers.smartnode;
//
//import com.moderndev.smarthome.data.domain.smartnode.SmartNode;
//import com.moderndev.smarthome.data.domain.smartnode.SmartNodeIdentity;
//import java.time.LocalDateTime;
//import java.time.temporal.ChronoUnit;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//import javax.annotation.PostConstruct;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import com.moderndev.smarthome.integration.services.utils.BeanUtil;
//import java.util.Objects;
//import java.util.stream.Collectors;
//import org.springframework.beans.BeanUtils;
//import org.springframework.stereotype.Component;
//import com.moderndev.smarthome.data.services.NodeService;
//
///**
// *
// * @author damian
// */
//@Slf4j
//@Component
//public class SmartNodeManager {
//    
//    private Set<SmartNode> nodes = Collections.synchronizedSet(new HashSet<SmartNode>());
//    
//    private NodeService nodeService;
//
//    public SmartNodeManager(NodeService nodeService) {
//        this.nodeService = nodeService;
//    }
//    
//    @PostConstruct
//    public void init(){
//        this.nodeService.findAll().stream().forEach(n -> this.nodes.add(n));
//        log.debug("loaded {} nodes", this.nodes.size());
//    }
//    
//    public Set<String> loggedClientIds(){
//        return transformToClientIdsCollection(this.nodes);
//    }
//    
//    public Set<String> registeredClientIds(){
////        return transformToClientIdsCollection(
////            this.nodes
////                .stream()
////                .filter(n -> n.));
//    }
//    
//    public Set<String> activeClientIds(){
//        
//    }
//    
//    SmartNode findById(String clientId){
//        
//    }
//    
//    private Set<String> transformToClientIdsCollection(Set<SmartNode> nodes){
//        return this.nodes
//                .stream()
//                .map(node -> { return new String(node.getClientId()); })
//                .collect(Collectors.toSet());
//    }
//   
//    //--------------------
//    
//    
//
//    
//    public void updateMetadata(String id, SmartNodeMetadata smartNodeMetadata){
//        
//    }
//    
//    public void register(String id, String name){
//        //TODO set custom name for node
//        
//    }
//    
//    public void unregister(String id){
//        
//    }
//    
//    private void activate(String id){
//        
//    }
//    
//    public boolean isActive(String id)
//    
//    
//    
//    
//    public 
//    
//    public SmartNode isSmartNodeLoggeed(SmartNode smartNode) throws SmartNodeManagerException{
//        ArrayList<SmartNode> nodes = this.nodes
//                .stream()
//                .filter(n -> n.equals(smartNode))
//                .collect(Collectors.toCollection(ArrayList::new));
//        
//        if(nodes.size() > 1){
//            throw new SmartNodeManagerException("found more than one logged SmartNode with the same ID");
//        }else if(nodes.size() == 0){
//            return null;
//        }else{
//            return nodes.get(0);
//        }
//    }
//    
//    public SmartNode loginSmartNode(SmartNode smartNode) throws SmartNodeManagerException{
//        SmartNode smartNodeLoggeed = isSmartNodeLoggeed(smartNode);
//        if(smartNodeLoggeed != null){
//            return smartNodeLoggeed;
//        }
//        
//        //TODO activate it
//        this.nodes.add(smartNode);
//        return smartNode;
//    }
//    
//    public boolean logoutSmartNode(SmartNode smartNode){
//        return false;
//    }
//    
//    public SmartNode isSmartNodeIdentified(SmartNode smartNode){
//        return null;
//    }
//    
//    public SmartNode identifySmartNode(SmartNode smartNode){
//        return null;
//    }
//    
//    public boolean dropSmartNodeIdentification(SmartNode smartNode){
//        return false;
//    }
//    
//    public SmartNode activateSmartNode(SmartNode smartNode){
//        return null;
//    }
//    
//    public boolean deactivateSmartNode(SmartNode smartNode){
//        return false;
//    }
//    
//    public boolean isNodeActive(SmartNode smartNode){
//        return false;
////        if(node == null){
////            return false;
////        }
////        
////        LocalDateTime currentTime = LocalDateTime.now();
////        LocalDateTime latestActiveDateTime = node.getLatestActiveDateTime();
////        long minInactiveInterval = node.getMinInactiveInterval();
////
////        long inactiveMinutes = latestActiveDateTime.until(currentTime, ChronoUnit.MINUTES);
////        if(inactiveMinutes <= minInactiveInterval){
////            return true;
////        }
////        
////        return false;
//    }
//    
//    
//
////    public SmartNode findAuthenticatedNode(String id){
////        if(nodes.size() == 0){
////            return null;
////        }
////        
////        synchronized(this.nodes){
////            SmartNode node = this.nodes
////                    .stream()
////                    .filter(n -> n.getNodeId().equals(id))
////                    .findAny()
////                    .orElse(null);
////            
////            return node;
////        }
////    }
////    
////    private SmartNode findAuthenticatedNode(SmartNode node){
////        if(node == null || node.getNodeId() == null){
////            return null;
////        }
////        
////        synchronized(this.nodes){
////            SmartNode searchNode = this.nodes
////                    .stream()
////                    .filter(n -> n.equals(node))
////                    .findAny()
////                    .orElse(null);
////            
////            return searchNode;
////        }
////    }
////    
////    public boolean authenticateNode(SmartNode node){
////        if(node == null){
////            return false;
////        }
////        
////        if(findAuthenticatedNode(node) == null){
////            this.nodes.add(node);
////            setNodeActive(node);
////            return true;
////        }
////        
////        return false;
////    }
////    
////    public boolean dropAuthenticatedNode(String nodeId){
////        if(nodeId == null){
////            return false;
////        }
////        
////        return dropAuthenticatedNode(findAuthenticatedNode(nodeId));
////    }
////    
////    public boolean dropAuthenticatedNode(SmartNode node){
////        if(node == null){
////            return false;
////        }
////        
////        if(findAuthenticatedNode(node) != null){
////            this.nodes.remove(node);
////            
////            if(isNodeIdentified(node)){
////                this.nodeService.delete(node);
////            }
////            
////            return true;
////        }
////        
////        return false;
////    }
////    
////    public boolean dropIdentification(SmartNode node){
////        if(node == null){
////            return false;
////        }
////        
////        if(isNodeIdentified(node)){
////            node.setNodeIdentity(null);
////            
////        }
////        
////        return false;
////    }
////    
////    public boolean identifyNode(String id, SmartNodeIdentity nodeIdentityData){
////        if(id == null || nodeIdentityData == null){
////            return false;
////        }
////        
////        return identifyNode(getNodeAuthenticated(id), nodeIdentityData);
////    }
////    
////    private boolean identifyNode(SmartNode node, SmartNodeIdentity nodeIdentity){
////        if(node == null || nodeIdentity == null){
////            return false;
////        }
////        
////        node.setNodeIdentity(nodeIdentity);
////        
////        this.nodeService.save(node);
////        
////        return true;
////    }
////    
////    public boolean setNodeActive(String id){
////        if(id == null){
////            return false;
////        }
////        
////        return setNodeActive(getNodeAuthenticated(id));
////    }
////    
////    private boolean setNodeActive(SmartNode node){
////        if(node == null){
////            return false;
////        }
////        
////        node.setLatestActiveDateTime(LocalDateTime.now());
////
////        if(isNodeIdentified(node)){
////            this.nodeService.save(node);
////        }
////
////        return true;
////    }
////    
////    public boolean isNodeActive(String id){
////        if(id == null){
////            return false;
////        }
////        
////        return isNodeActive(getNodeAuthenticated(id));
////    }
////    
////    private boolean isNodeActive(SmartNode node){
////        if(node == null){
////            return false;
////        }
////        
////        LocalDateTime currentTime = LocalDateTime.now();
////        LocalDateTime latestActiveDateTime = node.getLatestActiveDateTime();
////        long minInactiveInterval = node.getMinInactiveInterval();
////
////        long inactiveMinutes = latestActiveDateTime.until(currentTime, ChronoUnit.MINUTES);
////        if(inactiveMinutes <= minInactiveInterval){
////            return true;
////        }
////        
////        return false;
////    }
////    
////    public boolean isNodeIdentified(String id){
////        if(id == null){
////            return false;
////        }
////        
////        return isNodeIdentified(getNodeAuthenticated(id));
////    }
////    
////    private boolean isNodeIdentified(SmartNode node){
////        if(node == null){
////            return false;
////        }
////        
////        return node.getNodeIdentity() != null;
////    }
//}
