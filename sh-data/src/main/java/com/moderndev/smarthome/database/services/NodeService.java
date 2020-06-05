/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.database.services;

import com.moderndev.smarthome.data.domain.node.Node;

/**
 *
 * @author damian
 */
public interface NodeService {
    
    Node findById(long id);
}
