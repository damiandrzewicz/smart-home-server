/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.data.repository;

import com.moderndev.smarthome.data.TestConfig;
import com.moderndev.smarthome.data.domain.smartnode.SmartNode;
import com.moderndev.smarthome.data.domain.smartnode.SmartNodeIdentity;
import com.moderndev.smarthome.data.domain.smartnode.SmartNodeType;
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
public class SmartNodeRepositoryTest {
    
    private static Validator validator;
    
    @Autowired
    SmartNodeRepository nodeRepository;
    
    @Autowired
    SmartNodeTypeRepository nodeTypeRepository;
    
    static final String NodeId = "myNodeId";
    static final String NodeIdentityName = "myNodeIdentityName";
    SmartNode node;
    SmartNodeIdentity nodeIdentity;
      
    @BeforeAll
    static void setUpValidator(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    @BeforeEach
    void setUp() {
        this.node = new SmartNode(NodeId);
        nodeIdentity = new SmartNodeIdentity("myIdentity");
        this.node.setSmartNodeIdentity(nodeIdentity);
    }
    
    @Test
    void testSaveNode(){
        assertNotNull(nodeRepository.save(this.node).getId());
    }
    
    @Test
    void testSaveNodeWithNullNodeId() {
        this.node.setSmartNodeId(null);
        Set<ConstraintViolation<SmartNode>> constraintViolations = validator.validate(this.node);
        
        assertEquals(1, constraintViolations.size());
        assertEquals("must not be null", constraintViolations.iterator().next().getMessage());
    }

    @Test
    void testFindById() {
        SmartNode savedNode = nodeRepository.save(this.node);
        Optional<SmartNode> findById = nodeRepository.findById(savedNode.getId());
        assertTrue(findById.isPresent());
        assertNotNull(findById.get());
        assertEquals(NodeId, findById.get().getSmartNodeId());
    }

    @Test
    void testFindByNodeId(){
        nodeRepository.save(this.node);
        SmartNode foundNode = nodeRepository.findByNodeId(NodeId);
        assertEquals(NodeId, foundNode.getSmartNodeId());
    }

    @Test
    void testSaveNodeWithNodeType(){
        String nodeType = "myNodeType";
        SmartNodeType savedNodeType = nodeTypeRepository.save(new SmartNodeType(nodeType));
        assertNotNull(savedNodeType);
        
        this.node.setSmartNodeType(savedNodeType);
        SmartNode savedNode = nodeRepository.save(node);
        assertNotNull(savedNode);
        
        SmartNode savedNodeCopy = nodeRepository.findByNodeId(this.node.getSmartNodeId());
        assertEquals(nodeType, savedNodeCopy.getSmartNodeType().getType());
    }
    
    @Test
    void testDeleteNodeAndPersistNodeType(){
        String nodeType = "myNodeType";
        SmartNodeType savedNodeType = nodeTypeRepository.save(new SmartNodeType(nodeType));
        assertNotNull(savedNodeType);
        
        this.node.setSmartNodeType(savedNodeType);
        SmartNode savedNode = nodeRepository.save(this.node);
        assertNotNull(savedNode);
        
        nodeRepository.delete(savedNode);
        
        SmartNodeType foundNodeType = nodeTypeRepository.findByType(nodeType);
        assertEquals(nodeType, foundNodeType.getType());
    }
    
    @Test
    void testDeleteNode(){
        SmartNode savedNode = nodeRepository.save(this.node);
        nodeRepository.delete(savedNode);
        
        SmartNode findByNodeId = nodeRepository.findByNodeId(this.node.getSmartNodeId());
        assertNull(findByNodeId);
    }
    
    @Test
    void setSaveNodeWithoutIdentity(){
        this.node.setSmartNodeIdentity(null);
        
        Set<ConstraintViolation<SmartNode>> constraintViolations = validator.validate(this.node);
        
        assertEquals(1, constraintViolations.size());
        assertEquals("must not be null", constraintViolations.iterator().next().getMessage());
    }
    
}
