/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.node;

/**
 *
 * @author damian
 */
public class NodeInfo {
    
    private String id;
    private NodeType nodeType;
    private NodeSystemInfo nodeSystemInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeType nodeType) {
        this.nodeType = nodeType;
    }

    public NodeSystemInfo getNodeSystemInfo() {
        return nodeSystemInfo;
    }

    public void setNodeSystemInfo(NodeSystemInfo nodeSystemInfo) {
        this.nodeSystemInfo = nodeSystemInfo;
    }
    
    
}
