/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.data.domain.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author damian
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Credentials {
    
    private String login;
    private String password;

}
