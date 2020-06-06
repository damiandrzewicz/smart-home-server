/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.database.repository;

import com.moderndev.smarthome.SmartHomeDataApplication;
import com.moderndev.smarthome.data.domain.node.NodeType;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author damian
 */
@DataJpaTest
@Sql({"/insertNodeType.sql"})
public class NodeTypeRepositoryTest {
    
//    TestEntityManager entityManager;
//    
    @Autowired
    NodeTypeRepository nodeTypeRepository;
      
    @BeforeEach
    void setUp() {
    }
    
    @Test
    void testSave(){
        assertTrue(true);
    }

    @Test
    void testFindByType(){
        String nodeType = "nodeType1";
        Optional<NodeType> findByType = this.nodeTypeRepository.findByType(nodeType);
        assertTrue(findByType.isPresent());
        assertEquals(nodeType, findByType.get().getType());
    }
    

    
}
