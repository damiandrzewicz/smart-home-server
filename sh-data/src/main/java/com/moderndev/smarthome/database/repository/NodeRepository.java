/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.database.repository;

import com.moderndev.smarthome.data.domain.node.Node;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author damian
 */
public interface NodeRepository extends CrudRepository<Node, Long>{
    
    Node findById(long id);
    
}
