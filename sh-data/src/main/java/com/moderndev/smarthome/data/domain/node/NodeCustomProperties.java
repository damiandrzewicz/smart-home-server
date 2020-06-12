/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.data.domain.node;

import com.moderndev.smarthome.data.domain.base.BaseEntity;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author damian
 */
@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class NodeCustomProperties extends BaseEntity{
    
    private String name;
    
    private String description;
    
    //TODO block insert
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
        name = "node_custom_property_node_category",
        joinColumns = @JoinColumn(name = "custom_property_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<NodeCategory> categories;
}
