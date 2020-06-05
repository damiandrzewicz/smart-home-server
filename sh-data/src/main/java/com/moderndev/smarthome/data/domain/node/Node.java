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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
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
    
    @OneToOne(fetch = FetchType.EAGER)
    private NodeInfo nodeInfo = null;
    
    private LocalDateTime latestActiveDateTime = null;
    //private NodeIdentityData nodeIdentityData = null;
    
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
        if (!Objects.equals(this.nodeInfo, other.nodeInfo)) {
            return false;
        }
        return true;
    }

    
}
