/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.data.domain.node;

import com.moderndev.smarthome.data.domain.base.BaseEntity;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import javax.persistence.Entity;

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
public class NodeType extends BaseEntity{
    
    @NotNull
    @Column(unique = true)
    private String type;
}
