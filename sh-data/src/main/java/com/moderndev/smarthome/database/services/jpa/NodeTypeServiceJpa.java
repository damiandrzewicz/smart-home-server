/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.database.services.jpa;

import com.moderndev.smarthome.data.domain.node.NodeType;
import com.moderndev.smarthome.data.repository.NodeTypeRepository;

import com.moderndev.smarthome.data.services.NodeTypeService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author damian
 */
@Service
public class NodeTypeServiceJpa implements NodeTypeService{
    
    private final NodeTypeRepository nodeTypeRepository;

    public NodeTypeServiceJpa(NodeTypeRepository nodeTypeRepository) {
        this.nodeTypeRepository = nodeTypeRepository;
    }


    @Override
    public NodeType save(NodeType entity) {
        return this.nodeTypeRepository.save(entity);
    }

    @Override
    public List<NodeType> saveAll(List<NodeType> entities) {
        List<NodeType> nodes = new ArrayList<>();
        this.nodeTypeRepository.saveAll(entities).iterator().forEachRemaining(nodes::add);
        return nodes;
    }

    @Override
    public NodeType findById(Long id) {
        return this.nodeTypeRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<NodeType> findAll() {
        return IterableUtils.toList(this.nodeTypeRepository.findAll());
    }

    @Override
    public long count() {
        return this.nodeTypeRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(NodeType entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public NodeType findByType(String type) {
        return this.nodeTypeRepository.findByType(type);
    }

    @Override
    public void deleteByType(String type) {
        this.nodeTypeRepository.deleteByType(type);
    }
    
}
