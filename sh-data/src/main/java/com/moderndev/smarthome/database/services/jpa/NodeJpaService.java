/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.database.services.jpa;

import com.moderndev.smarthome.data.domain.node.Node;
import com.moderndev.smarthome.database.repository.NodeRepository;
import com.moderndev.smarthome.database.services.NodeService;
import org.springframework.stereotype.Service;

/**
 *
 * @author damian
 */
@Service
public class NodeJpaService implements NodeService{
  
    private final NodeRepository nodeRepository;

    public NodeJpaService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    @Override
    public Node findById(long id) {
        return nodeRepository.findById(id);
    }
    
    
}
