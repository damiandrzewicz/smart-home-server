/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.services.node;

import com.moderndev.smarthome.data.domain.node.Node;
import com.moderndev.smarthome.data.domain.node.NodeIdentity;
import com.moderndev.smarthome.database.services.NodeService;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author damian
 */
@Slf4j
@Service
public class NodeManager {
   
    private Set<Node> nodes = Collections.synchronizedSet(new HashSet<Node>());
    
    private NodeService nodeService;

    public NodeManager(NodeService nodeService) {
        this.nodeService = nodeService;

    }
    
    @PostConstruct
    public void init(){
        this.nodeService.findAll().stream().forEach(n -> this.nodes.add(n));
        log.debug("loaded {} nodes", this.nodes.size());
    }
    

    public Node getNodeAuthenticated(String id){
        if(nodes.size() == 0){
            return null;
        }
        
        synchronized(this.nodes){
            Node node = this.nodes
                    .stream()
                    .filter(n -> n.getNodeId().equals(id))
                    .findAny()
                    .orElse(null);
            
            return node;
        }
    }
    
    private Node getNodeAuthenticated(Node node){
        if(node == null || node.getNodeId() == null){
            return null;
        }
        
        synchronized(this.nodes){
            Node searchNode = this.nodes
                    .stream()
                    .filter(n -> n.equals(node))
                    .findAny()
                    .orElse(null);
            
            return searchNode;
        }
    }
    
    public boolean authenticateNode(Node node){
        if(node == null){
            return false;
        }
        
        if(getNodeAuthenticated(node) == null){
            this.nodes.add(node);
            setNodeActive(node);
            return true;
        }
        
        return false;
    }
    
    public boolean dropAuthenticatedNode(String nodeId){
        if(nodeId == null){
            return false;
        }
        
        return dropAuthenticatedNode(getNodeAuthenticated(nodeId));
    }
    
    public boolean dropAuthenticatedNode(Node node){
        if(node == null){
            return false;
        }
        
        if(getNodeAuthenticated(node) != null){
            this.nodes.remove(node);
            
            if(isNodeIdentified(node)){
                this.nodeService.delete(node);
            }
            
            return true;
        }
        
        return false;
    }
    
    public boolean dropIdentification(Node node){
        if(node == null){
            return false;
        }
        
        if(isNodeIdentified(node)){
            node.setNodeIdentity(null);
            
        }
        
        return false;
    }
    
    public boolean identifyNode(String id, NodeIdentity nodeIdentityData){
        if(id == null || nodeIdentityData == null){
            return false;
        }
        
        return identifyNode(getNodeAuthenticated(id), nodeIdentityData);
    }
    
    private boolean identifyNode(Node node, NodeIdentity nodeIdentity){
        if(node == null || nodeIdentity == null){
            return false;
        }
        
        node.setNodeIdentity(nodeIdentity);
        
        this.nodeService.save(node);
        
        return true;
    }
    
    public boolean setNodeActive(String id){
        if(id == null){
            return false;
        }
        
        return setNodeActive(getNodeAuthenticated(id));
    }
    
    private boolean setNodeActive(Node node){
        if(node == null){
            return false;
        }
        
        node.setLatestActiveDateTime(LocalDateTime.now());

        if(isNodeIdentified(node)){
            this.nodeService.save(node);
        }

        return true;
    }
    
    public boolean isNodeActive(String id){
        if(id == null){
            return false;
        }
        
        return isNodeActive(getNodeAuthenticated(id));
    }
    
    private boolean isNodeActive(Node node){
        if(node == null){
            return false;
        }
        
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime latestActiveDateTime = node.getLatestActiveDateTime();
        long minInactiveInterval = node.getMinInactiveInterval();

        long inactiveMinutes = latestActiveDateTime.until(currentTime, ChronoUnit.MINUTES);
        if(inactiveMinutes <= minInactiveInterval){
            return true;
        }
        
        return false;
    }
    
    public boolean isNodeIdentified(String id){
        if(id == null){
            return false;
        }
        
        return isNodeIdentified(getNodeAuthenticated(id));
    }
    
    private boolean isNodeIdentified(Node node){
        if(node == null){
            return false;
        }
        
        return node.getNodeIdentity() != null;
    }
}
