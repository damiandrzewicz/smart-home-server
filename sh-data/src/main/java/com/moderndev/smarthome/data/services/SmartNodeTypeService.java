/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.data.services;

import com.moderndev.smarthome.data.domain.smartnode.SmartNodeType;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author damian
 */
public interface SmartNodeTypeService {
    
    SmartNodeType save(SmartNodeType entity);

    List<SmartNodeType> saveAll(List<SmartNodeType> entities);

    SmartNodeType findById(Long id);
    
    SmartNodeType findByType(String type);

    boolean existsById(Long id);

    List<SmartNodeType> findAll();

    long count();

    void deleteById(Long id);

    void delete(SmartNodeType entity);

    void deleteAll();
    
    void deleteByType(String type);
}
