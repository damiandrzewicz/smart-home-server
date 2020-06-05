/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.services.node;

import com.moderndev.smarthome.data.domain.node.Node;
import com.moderndev.smarthome.data.domain.node.NodeIdentityData;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author damian
 */
public class NodeManager {
   
    private List<Node> nodes = Collections.synchronizedList(new ArrayList<>());
    

    public Node findNode(String id){
        if(nodes.size() == 0){
            return null;
        }
        
        synchronized(this.nodes){
            Node node = this.nodes
                    .stream()
                    .filter(n -> n.getNodeInfo().equals(id))
                    .findAny()
                    .orElse(null);
            
            return node;
        }
    }
    
    private Node findNode(Node node){
        if(node == null || node.getNodeInfo().getNodeId() == null){
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
    
    public boolean addNode(String id){
        if(id == null){
            return false;
        }
        
        var node = new Node();
        node.getNodeInfo().setNodeId(id);
        
        return addNode(node);
    }
    
    public boolean addNode(Node node){
        if(node == null){
            return false;
        }
        
        if(findNode(node) == null){
            this.nodes.add(node);
            setNodeActive(node);
            return true;
        }
        
        return false;
    }
    
    public boolean identifyNode(String id, NodeIdentityData nodeIdentityData){
        if(id == null || nodeIdentityData == null){
            return false;
        }
        
        return identifyNode(findNode(id), nodeIdentityData);
    }
    
    private boolean identifyNode(Node node, NodeIdentityData nodeIdentityData){
        if(node == null || nodeIdentityData == null){
            return false;
        }
        
        //node.setNodeIdentityData(nodeIdentityData);
        //TODO save to databse
        return true;
    }
    
    public boolean setNodeActive(String id){
        if(id == null){
            return false;
        }
        
        return setNodeActive(findNode(id));
    }
    
    private boolean setNodeActive(Node node){
        if(node == null){
            return false;
        }
        
        node.setLatestActiveDateTime(LocalDateTime.now());

        if(isNodeIdentified(node)){
            //TODO update database if identified
        }

        return true;
    }
    
    public boolean isNodeActive(String id){
        if(id == null){
            return false;
        }
        
        return isNodeActive(findNode(id));
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
        
        return isNodeIdentified(findNode(id));
    }
    
    private boolean isNodeIdentified(Node node){
        if(node == null){
            return false;
        }
        
        return false;
        //return (node.getNodeIdentityData() != null);
    }
}
