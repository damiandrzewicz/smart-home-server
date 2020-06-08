/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.data.domain.smartnode;

import com.moderndev.smarthome.data.domain.base.BaseEntity;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
/**
 *
 * @author damian
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SmartNode extends BaseEntity {
    
    @NotNull
    @Column(unique = true)
    private String smartNodeId;
    
    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "smart_node_identity_id")
    private SmartNodeIdentity smartNodeIdentity = null;
    
    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "smart_node_type_id")
    private SmartNodeType smartNodeType = null;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "smart_node_system_info_id")
    private SmartNodeSystemInfo smartNodeSystemInfo;
    
    private LocalDateTime latestActiveDateTime = null;
   
    private long minInactiveInterval = 1;

    public SmartNode(String smartNodeId) {
        this.smartNodeId = smartNodeId;
    }
    
    public SmartNode(String smartNodeId, SmartNodeType smartNodeType, SmartNodeIdentity smartNodeIdentity){
        this.smartNodeId = smartNodeId;
        this.smartNodeType = smartNodeType;
        this.smartNodeIdentity = smartNodeIdentity;
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
        final SmartNode other = (SmartNode) obj;
        if (!Objects.equals(this.smartNodeId, other.smartNodeId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.smartNodeId);
        return hash;
    }
    
    

    
}
