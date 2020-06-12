///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.moderndev.smarthome.data.repository;
//
//import com.moderndev.smarthome.data.TestConfig;
//import com.moderndev.smarthome.data.domain.smartnode.SmartNode;
//import com.moderndev.smarthome.data.domain.smartnode.SmartNodeType;
//import java.util.Optional;
//import java.util.Set;
//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;
//import org.hibernate.exception.ConstraintViolationException;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.dao.DataIntegrityViolationException;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.context.junit4.SpringRunner;
//import com.moderndev.smarthome.data.repository.NodeTypeRepository;
//
///**
// *
// * @author damian
// */
//
////Nice to read
////https://stackoverflow.com/a/52239514/6281636
//
//@DataJpaTest
////@Sql({"/sql/insert_node_type.sql"})
//@Import(TestConfig.class)
//class NodeTypeRepositoryTest {
//    
//    private final String NodeType1 = "nodeType1";
//    private final String NodeType2 = "nodeType2";
//    private final String NodeType3 = "nodeType3";
//      
//    @Autowired
//    NodeTypeRepository nodeTypeRepository;
//    
//    private static Validator validator;
//      
//    @BeforeAll
//    static void setUpValidator(){
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        validator = factory.getValidator();
//    }
//    
//    @Test
//    void testSave(){
//        SmartNodeType saveNodeType = nodeTypeRepository.save(new SmartNodeType(NodeType1));
//        assertNotNull(saveNodeType);
//        assertNotNull(saveNodeType.getId());
//        assertEquals(NodeType1, saveNodeType.getType());
//    }
//    
//    @Test
//    void testSaveExisting(){
//        var nodeType = new SmartNodeType(NodeType1);
//        var copyNodeType = new SmartNodeType(NodeType1);
//        
//        assertThrows(DataIntegrityViolationException.class, () -> { 
//            nodeTypeRepository.save(nodeType);
//            nodeTypeRepository.save(copyNodeType);
//        } );
//    }
//    
//    @Test
//    void setSaveNodeTypeIsNull(){
//        var nodeType = new SmartNodeType(null);
//        Set<ConstraintViolation<SmartNodeType>> constraintViolations = validator.validate(nodeType);
//        
//        assertEquals(1, constraintViolations.size());
//        assertEquals("must not be null", constraintViolations.iterator().next().getMessage());
//    }
//
//    @Test
//    void testFindByType(){
//        nodeTypeRepository.save(new SmartNodeType(NodeType1));
//        assertNotNull(this.nodeTypeRepository.findByType(NodeType1));
//    }
//    
//    @Test
//    void testDelete(){
//        SmartNodeType nodeType = nodeTypeRepository.save(new SmartNodeType(NodeType1));
//        nodeTypeRepository.delete(nodeType);
//        
//        Optional<SmartNodeType> findById = nodeTypeRepository.findById(nodeType.getId());
//        assertTrue(findById.isEmpty());
//    }
//    
//    @Test
//    void testDeleteAll(){
//        nodeTypeRepository.save(new SmartNodeType(NodeType1));
//        nodeTypeRepository.save(new SmartNodeType(NodeType2));
//        nodeTypeRepository.save(new SmartNodeType(NodeType3));
//        
//        nodeTypeRepository.deleteAll();
//        assertEquals(0, nodeTypeRepository.count());
//    }
//    
//    @Test 
//    void testCount(){
//        assertEquals(0, nodeTypeRepository.count());
//        
//        nodeTypeRepository.save(new SmartNodeType(NodeType1));
//        assertEquals(1, nodeTypeRepository.count());
//        
//        nodeTypeRepository.save(new SmartNodeType(NodeType2));
//        assertEquals(2, nodeTypeRepository.count());
//        
//        nodeTypeRepository.save(new SmartNodeType(NodeType3));
//        assertEquals(3, nodeTypeRepository.count());
//    }
//    
//    @Test
//    void testDeleteByType(){
//        SmartNodeType nodeType = nodeTypeRepository.save(new SmartNodeType(NodeType1));
//        nodeTypeRepository.deleteByType(NodeType1);
//        
//        Optional<SmartNodeType> foundNode = nodeTypeRepository.findById(nodeType.getId());
//        assertTrue(foundNode.isEmpty());
//    }
//}
