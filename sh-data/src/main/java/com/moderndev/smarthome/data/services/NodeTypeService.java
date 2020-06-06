/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.database.services;

import com.moderndev.smarthome.data.domain.node.NodeType;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author damian
 */
public interface NodeTypeService {
    
    NodeType save(NodeType entity);

    List<NodeType> saveAll(List<NodeType> entities);

    Optional<NodeType> findById(Long id);
    
    Optional<NodeType> findByType(String type);

    boolean existsById(Long id);

    List<NodeType> findAll();

    long count();

    void deleteById(Long id);

    void delete(NodeType entity);

    void deleteAll();
}
