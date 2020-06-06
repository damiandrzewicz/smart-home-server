/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.bootstrap;

import com.moderndev.smarthome.data.domain.node.Node;
import com.moderndev.smarthome.data.domain.node.NodeIdentity;
import com.moderndev.smarthome.data.domain.node.NodeSystemInfo;
import com.moderndev.smarthome.data.domain.node.NodeType;
import com.moderndev.smarthome.database.services.NodeService;
import com.moderndev.smarthome.database.services.NodeTypeService;
import com.moderndev.smarthome.integration.services.node.NodeManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 *
 * @author damian
 */
@Component
@Profile("dev")
@Slf4j
public class DataLoader implements CommandLineRunner{

    private final NodeService nodeService;
    private final NodeTypeService nodeTypeService;
    
    private final NodeManager nodeManager;

    public DataLoader(NodeService nodeService, NodeTypeService nodeTypeService, NodeManager nodeManager) {
        this.nodeService = nodeService;
        this.nodeTypeService = nodeTypeService;
        this.nodeManager = nodeManager;
    }
    
    @Override
    public void run(String... args) throws Exception {
        
        //create new node types
        this.nodeTypeService.save(new NodeType("type1"));
        this.nodeTypeService.save(new NodeType("type2"));
        this.nodeTypeService.save(new NodeType("type3"));
        this.nodeTypeService.save(new NodeType("type4"));
        this.nodeTypeService.save(new NodeType("type5"));
        this.nodeTypeService.save(new NodeType("type6"));
        
        //node system info
        NodeSystemInfo nodeSystemInfo = new NodeSystemInfo();
        nodeSystemInfo.setFreeHeapSize(123);
        nodeSystemInfo.setMinFreeHeapSize(12);
        nodeSystemInfo.setMyHomeVersion("0.0.1");
        nodeSystemInfo.setSdkVersion("1.0.1");
        
        
        //node type
        var nodeType = this.nodeTypeService.findByType("type1");
        if(nodeType.isEmpty() || nodeType.get() == null){
            log.error("missing node type in DB: 'type1'");
            return;
        }
        
        //node identity
        var nodeIdentity = new NodeIdentity("MyOwnnodeName", "Kitchen");
        
        
        var node = new Node();
        node.setNodeId("id123");
        node.setNodeIdentity(nodeIdentity);
        node.setNodeSystemInfo(nodeSystemInfo);
        node.setNodeType(nodeType.get());
        nodeService.save(node);
        
        //Load data into node manager
        this.nodeManager.init();
    }
    
}
