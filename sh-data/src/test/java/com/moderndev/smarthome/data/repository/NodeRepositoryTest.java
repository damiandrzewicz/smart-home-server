/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.data.repository;

import com.moderndev.smarthome.data.TestConfig;
import com.moderndev.smarthome.data.domain.node.Node;
import com.moderndev.smarthome.data.domain.node.NodeIdentity;
import com.moderndev.smarthome.data.domain.node.NodeType;
import java.util.Optional;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

/**
 *
 * @author damian
 */
@DataJpaTest
@Import(TestConfig.class)
public class NodeRepositoryTest {
    
    private static Validator validator;
    
    @Autowired
    NodeRepository nodeRepository;
    
    @Autowired
    NodeTypeRepository nodeTypeRepository;
    
    static final String NodeId = "myNodeId";
    static final String NodeIdentityName = "myNodeIdentityName";
    Node node;
    NodeIdentity nodeIdentity;
      
    @BeforeAll
    static void setUpValidator(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @BeforeEach
    void setUp() {
        this.node = new Node(NodeId);
        nodeIdentity = new NodeIdentity("myIdentity");
        this.node.setNodeIdentity(nodeIdentity);
    }
    
    @Test
    void testSaveNode(){
        assertNotNull(nodeRepository.save(this.node).getId());
    }
    
    @Test
    void testSaveNodeWithNullNodeId() {
        this.node.setNodeId(null);
        Set<ConstraintViolation<Node>> constraintViolations = validator.validate(this.node);
        
        assertEquals(1, constraintViolations.size());
        assertEquals("must not be null", constraintViolations.iterator().next().getMessage());
    }

    @Test
    void testFindById() {
        Node savedNode = nodeRepository.save(this.node);
        Optional<Node> findById = nodeRepository.findById(savedNode.getId());
        assertTrue(findById.isPresent());
        assertNotNull(findById.get());
        assertEquals(NodeId, findById.get().getNodeId());
    }

    @Test
    void testFindByNodeId(){
        nodeRepository.save(this.node);
        Node foundNode = nodeRepository.findByNodeId(NodeId);
        assertEquals(NodeId, foundNode.getNodeId());
    }

    @Test
    void testSaveNodeWithNodeType(){
        String nodeType = "myNodeType";
        NodeType savedNodeType = nodeTypeRepository.save(new NodeType(nodeType));
        assertNotNull(savedNodeType);
        
        this.node.setNodeType(savedNodeType);
        Node savedNode = nodeRepository.save(node);
        assertNotNull(savedNode);
        
        Node savedNodeCopy = nodeRepository.findByNodeId(this.node.getNodeId());
        assertEquals(nodeType, savedNodeCopy.getNodeType().getType());
    }
    
    @Test
    void testDeleteNodeAndPersistNodeType(){
        String nodeType = "myNodeType";
        NodeType savedNodeType = nodeTypeRepository.save(new NodeType(nodeType));
        assertNotNull(savedNodeType);
        
        this.node.setNodeType(savedNodeType);
        Node savedNode = nodeRepository.save(this.node);
        assertNotNull(savedNode);
        
        nodeRepository.delete(savedNode);
        
        NodeType foundNodeType = nodeTypeRepository.findByType(nodeType);
        assertEquals(nodeType, foundNodeType.getType());
    }
    
    @Test
    void testDeleteNode(){
        Node savedNode = nodeRepository.save(this.node);
        nodeRepository.delete(savedNode);
        
        Node findByNodeId = nodeRepository.findByNodeId(this.node.getNodeId());
        assertNull(findByNodeId);
    }
    
    @Test
    void setSaveNodeWithoutIdentity(){
        this.node.setNodeIdentity(null);
        
        Set<ConstraintViolation<Node>> constraintViolations = validator.validate(this.node);
        
        assertEquals(1, constraintViolations.size());
        assertEquals("must not be null", constraintViolations.iterator().next().getMessage());
    }
    
}
