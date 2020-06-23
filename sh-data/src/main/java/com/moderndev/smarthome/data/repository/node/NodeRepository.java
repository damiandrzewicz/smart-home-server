/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.data.repository.node;

import com.moderndev.smarthome.data.domain.node.Node;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author damian
 */
@Repository
public interface NodeRepository extends CrudRepository<Node, Long>{
    
    Optional<Node> findById(Long id);
    
    Node findByClientId(String clientId);
    
    Node save(Node node);
    
    @Query("update Node n set n.latestActive = :localDateTime where n.clientId = :clientId")
    void updateLatestActive(@Param("clientId")String clientId, @Param("localDateTime")LocalDateTime localDateTime);
}
