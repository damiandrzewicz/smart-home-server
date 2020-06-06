/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.database.services.jpa;

import com.moderndev.smarthome.data.domain.node.Node;
import com.moderndev.smarthome.database.repository.NodeRepository;
import com.moderndev.smarthome.database.services.NodeService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author damian
 */
@Service
public class NodeServiceJpa implements NodeService{
  
    private final NodeRepository nodeRepository;

    public NodeServiceJpa(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    @Override
    public Node save(Node entity) {
        return this.nodeRepository.save(entity);
    }

    @Override
    public List<Node> saveAll(List<Node> entities) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<Node> findById(Long id) {
        return this.nodeRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Node> findAll() {
        return IterableUtils.toList(this.nodeRepository.findAll());
    }

    @Override
    public long count() {
        return this.nodeRepository.count();
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Node entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll(List<Node> entities) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
    
}
