/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.data.services;

import com.moderndev.smarthome.data.domain.smartnode.SmartNode;
import java.util.List;
import java.util.Set;
import java.util.Optional;

/**
 *
 * @author damian
 */
public interface SmartNodeService {
    
    SmartNode save(SmartNode entity);

    List<SmartNode> saveAll(List<SmartNode> entities);

    SmartNode findById(Long id);
    
    SmartNode findByNodeId(String nodeId);

    boolean existsById(Long id);

    List<SmartNode> findAll();

    long count();

    void deleteById(Long id);

    void delete(SmartNode entity);

    void deleteAll(List<SmartNode> entities);

    void deleteAll();
}
