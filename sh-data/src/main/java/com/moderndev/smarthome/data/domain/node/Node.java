/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.data.domain.node;

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
/**
 *
 * @author damian
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Node extends BaseEntity {
    
    private String nodeId;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "node_identity_id")
    private NodeIdentity nodeIdentity = null;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "node_type_id")
    private NodeType nodeType;
    
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "node_system_info_id")
    private NodeSystemInfo nodeSystemInfo;
    
    private LocalDateTime latestActiveDateTime = null;
   
    private long minInactiveInterval = 1;
    
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
        if (!Objects.equals(this.nodeId, other.nodeId)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.nodeId);
        return hash;
    }
    
    

    
}
