/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.node;

import java.time.LocalDateTime;
/**
 *
 * @author damian
 */
public class Node {
    
    private NodeInfo nodeInfo =  null;
    private LocalDateTime latestActiveDatetime = null;
    private NodeIdentityData nodeIdentityData = null;
    private long minInactiveInterval = 1;
    
    
    

//    public NodeInfo getNodeInfo() {
//        return nodeInfo;
//    }
//
//    public void setNodeInfo(NodeInfo nodeInfo) {
//        this.nodeInfo = nodeInfo;
//    }
//    
//    public void setActive(){
//        this.latestActive = LocalDateTime.now();
//    }
//    
//    public void setInactive(){
//        this.latestActive = null;
//    }
//    
//    public boolean isActive(){
//        if(this.latestActive == null){
//            return false;
//        }
//        LocalDateTime currentTime = LocalDateTime.now();
//        long elapsedMinutes = latestActive.until(currentTime, ChronoUnit.MINUTES);
//        
//        return (elapsedMinutes > minInactiveInterval);
//    }
//    
//    public void identyfyNode(NodeIdentityData nodeIdentityData){
//        
//    }

    public NodeInfo getNodeInfo() {
        return nodeInfo;
    }

    public void setNodeInfo(NodeInfo nodeInfo) {
        this.nodeInfo = nodeInfo;
    }

    public LocalDateTime getLatestActiveDateTime() {
        return this.latestActiveDatetime;
    }

    public void setLatestActive(LocalDateTime latestActiveDateTime) {
        this.latestActiveDatetime = latestActiveDatetime;
    }

    public NodeIdentityData getNodeIdentityData() {
        return nodeIdentityData;
    }

    public void setNodeIdentityData(NodeIdentityData nodeIdentityData) {
        this.nodeIdentityData = nodeIdentityData;
    }

    public long getMinInactiveInterval() {
        return minInactiveInterval;
    }

    public void setMinInactiveInterval(long minInactiveInterval) {
        this.minInactiveInterval = minInactiveInterval;
    }
}
