/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.data.repository;

import com.moderndev.smarthome.data.domain.node.NodeType;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author damian
 */
@Repository
public interface NodeTypeRepository extends CrudRepository<NodeType, Long>{
    
    NodeType findByType(String type);
    
    void deleteByType(String type);
   
}
