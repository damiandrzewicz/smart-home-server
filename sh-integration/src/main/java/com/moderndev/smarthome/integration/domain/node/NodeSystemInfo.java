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
public class NodeSystemInfo {
    
    private String myHomeVersion;
    private String sdkVersion;
    private int freeHeapSize;
    private int minFreeHeapSize;

    public String getMyHomeVersion() {
        return myHomeVersion;
    }

    public void setMyHomeVersion(String myHomeVersion) {
        this.myHomeVersion = myHomeVersion;
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    public int getFreeHeapSize() {
        return freeHeapSize;
    }

    public void setFreeHeapSize(int freeHeapSize) {
        this.freeHeapSize = freeHeapSize;
    }

    public int getMinFreeHeapSize() {
        return minFreeHeapSize;
    }

    public void setMinFreeHeapSize(int minFreeHeapSize) {
        this.minFreeHeapSize = minFreeHeapSize;
    }
}
