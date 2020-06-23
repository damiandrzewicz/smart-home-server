/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.data.domain.node;

import com.moderndev.smarthome.data.domain.base.BaseEntity;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AccessLevel;

/**
 *
 * @author damian
 */
@Slf4j
@Getter()
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Node extends BaseEntity{
    
    @NotNull
    @Column(unique = true)
    private String clientId;
    
    @Column
    private Integer minInactiveInterval = 60;
     
    @NotNull
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "type_id")
    private NodeType type;
    
    @NotNull
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "os_info_id")
    private NodeOsInfo osInfo;
    
    private LocalDateTime latestActive;
    
    @NotNull
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "custom_properties_id")
    private NodeCustomProperties customProperties;
    
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "location_properties_id")
    private NodeLocationProperties locationProperties;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    @JoinColumn(name = "node_id")
    @Setter(AccessLevel.NONE)
    private List<NodeMemoryEntry> memoryDairy;

    public Node(String clientId) {
        this.clientId = clientId;
    }

    public void add(NodeMemoryEntry memoryEntry){
        if(memoryDairy == null){
            this.memoryDairy = new ArrayList<>();
        }
        this.memoryDairy.add(memoryEntry);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.clientId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        if (!Objects.equals(this.clientId, other.clientId)) {
            return false;
        }
        return true;
    }
    
    
}
