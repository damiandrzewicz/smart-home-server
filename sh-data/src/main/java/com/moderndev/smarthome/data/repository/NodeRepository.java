/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.database.repository;

import com.moderndev.smarthome.data.domain.node.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author damian
 */
@Repository
public interface NodeRepository extends JpaRepository<Node, Long>{
    
    Node findById(long id);
    
    Node save(Node node);
}
