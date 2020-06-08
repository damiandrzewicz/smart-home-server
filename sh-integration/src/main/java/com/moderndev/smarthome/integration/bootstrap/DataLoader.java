/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.bootstrap;

import com.moderndev.smarthome.data.domain.smartnode.SmartNode;
import com.moderndev.smarthome.data.domain.smartnode.SmartNodeIdentity;
import com.moderndev.smarthome.data.domain.smartnode.SmartNodeSystemInfo;
import com.moderndev.smarthome.data.domain.smartnode.SmartNodeType;
import com.moderndev.smarthome.integration.services.managers.SmartNodeManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import com.moderndev.smarthome.data.services.SmartNodeService;
import com.moderndev.smarthome.data.services.SmartNodeTypeService;

/**
 *
 * @author damian
 */
@Component
@Profile("dev")
@Slf4j
public class DataLoader implements CommandLineRunner{

    private final SmartNodeService nodeService;
    private final SmartNodeTypeService nodeTypeService;
    
    private final SmartNodeManager nodeManager;

    public DataLoader(SmartNodeService nodeService, SmartNodeTypeService nodeTypeService, SmartNodeManager nodeManager) {
        this.nodeService = nodeService;
        this.nodeTypeService = nodeTypeService;
        this.nodeManager = nodeManager;
    }
    
    @Override
    public void run(String... args) throws Exception {
        
        //create new node types
        this.nodeTypeService.save(new SmartNodeType("type1"));
        this.nodeTypeService.save(new SmartNodeType("type2"));
        this.nodeTypeService.save(new SmartNodeType("type3"));
        this.nodeTypeService.save(new SmartNodeType("type4"));
        this.nodeTypeService.save(new SmartNodeType("type5"));
        this.nodeTypeService.save(new SmartNodeType("type6"));
        
        //node system info
        SmartNodeSystemInfo nodeSystemInfo = new SmartNodeSystemInfo();
        nodeSystemInfo.setFreeHeapSize(123);
        nodeSystemInfo.setMinFreeHeapSize(12);
        nodeSystemInfo.setMyHomeVersion("0.0.1");
        nodeSystemInfo.setSdkVersion("1.0.1");
        
        
        //node type
        var nodeType = this.nodeTypeService.findByType("type1");
        if(nodeType == null){
            log.error("missing node type in DB: 'type1'");
            return;
        }
        
        //node identity
        var nodeIdentity = new SmartNodeIdentity("MyOwnnodeName", "Kitchen");
        
        
        var node = new SmartNode();
        node.setSmartNodeId("id123");
        node.setSmartNodeIdentity(nodeIdentity);
        node.setSmartNodeSystemInfo(nodeSystemInfo);
        node.setSmartNodeType(nodeType);
        nodeService.save(node);
        
        //Load data into node manager
        this.nodeManager.init();
    }
    
}
