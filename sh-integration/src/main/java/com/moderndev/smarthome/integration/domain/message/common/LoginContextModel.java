/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.message.common;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author damian
 */
@Getter
@Setter
public class LoginContextModel{
    
    public String login;
    
    public String password;
}
