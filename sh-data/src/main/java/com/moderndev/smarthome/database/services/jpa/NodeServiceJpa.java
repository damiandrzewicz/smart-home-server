///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.moderndev.smarthome.database.services.jpa;
//
//import com.moderndev.smarthome.data.domain.smartnode.SmartNode;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//import org.apache.commons.collections4.IterableUtils;
//import org.springframework.stereotype.Service;
//import com.moderndev.smarthome.data.repository.NodeRepository;
//import com.moderndev.smarthome.data.services.NodeService;
//
///**
// *
// * @author damian
// */
//@Service
//public class NodeServiceJpa implements NodeService{
//  
//    private final NodeRepository smartNodeRepository;
//
//    public NodeServiceJpa(NodeRepository nodeRepository) {
//        this.smartNodeRepository = nodeRepository;
//    }
//
//    @Override
//    public SmartNode save(SmartNode entity) {
//        return this.smartNodeRepository.save(entity);
//    }
//
//    @Override
//    public List<SmartNode> saveAll(List<SmartNode> entities) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public SmartNode findById(Long id) {
//        return this.smartNodeRepository.findById(id).orElse(null);
//    }
//
//    @Override
//    public boolean existsById(Long id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public List<SmartNode> findAll() {
//        return IterableUtils.toList(this.smartNodeRepository.findAll());
//    }
//
//    @Override
//    public long count() {
//        return this.smartNodeRepository.count();
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void delete(SmartNode entity) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void deleteAll(List<SmartNode> entities) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public void deleteAll() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public SmartNode findByNodeId(String nodeId) {
//        return smartNodeRepository.findByNodeId(nodeId);
//    }
//   
//    
//}
