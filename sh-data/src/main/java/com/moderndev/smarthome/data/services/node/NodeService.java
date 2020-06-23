/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.data.services.node;

import com.moderndev.smarthome.data.domain.node.Node;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.Optional;

/**
 *
 * @author damian
 */
public interface NodeService {
    
    Node save(Node entity);

    List<Node> saveAll(List<Node> entities);

    Node findById(Long id);
    
    Node findByClientId(String clientId);

    boolean existsById(Long id);

    List<Node> findAll();

    long count();

    void deleteById(Long id);

    void delete(Node entity);

    void deleteAll(List<Node> entities);

    void deleteAll();
    
    void updateLatestActive(String clientId, LocalDateTime localDateTime);
}
