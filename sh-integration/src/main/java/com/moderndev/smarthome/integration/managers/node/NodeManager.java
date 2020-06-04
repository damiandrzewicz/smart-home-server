/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.managers.node;

import com.moderndev.smarthome.integration.domain.node.Node;
import com.moderndev.smarthome.integration.domain.node.NodeIdentityData;
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
    
    public Node findNodeById(String id){
        if(nodes.size() == 0){
            return null;
        }
        
        synchronized(this.nodes){
            Optional<Node> node = this.nodes
                    .stream()
                    .filter(n -> n.getNodeInfo().equals(id))
                    .findFirst();
            
            return node.orElseGet(null);
        }
    }
    
    public boolean addNode(Node node){
        if(node == null){
            return false;
        }
        
        if(findNodeById(node.getNodeInfo().getId()) == null){
            this.nodes.add(node);
            return true;
        }
        
        return false;
    }
    
    public boolean identifyNode(String id, NodeIdentityData nodeIdentityData){
        if(id == null || nodeIdentityData == null){
            return false;
        }
        
        Node node = findNodeById(id);
        if(node != null){
            node.setNodeIdentityData(nodeIdentityData);
            return true;
        }
        
        return false;
    }
    
    public boolean setNodeActive(String id){
        if(id == null){
            return false;
        }
        
        Node node = findNodeById(id);
        if(node != null){
            node.setLatestActive(LocalDateTime.now());
            return true;
        }
        
        return false;
    }
    
    public boolean isNodeActive(String id){
        if(id == null){
            return false;
        }
        
        Node node = findNodeById(id);
        if(node != null){
            LocalDateTime currentTime = LocalDateTime.now();
            LocalDateTime latestActiveDateTime = node.getLatestActiveDateTime();
            long minInactiveInterval = node.getMinInactiveInterval();
            
            long inactiveMinutes = latestActiveDateTime.until(currentTime, ChronoUnit.MINUTES);
            if(inactiveMinutes <= minInactiveInterval){
                return true;
            }
        }
        
        return false;
    }
}
