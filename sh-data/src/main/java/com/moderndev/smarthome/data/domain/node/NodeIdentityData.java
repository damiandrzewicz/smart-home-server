/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.data.domain.node;

import com.moderndev.smarthome.data.domain.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;

/**
 *
 * @author damian
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class NodeIdentityData extends BaseEntity{
    
    private String name;
    private String location;
}
