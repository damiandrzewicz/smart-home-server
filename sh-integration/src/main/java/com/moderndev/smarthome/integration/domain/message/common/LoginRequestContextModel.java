/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.message.common;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author damian
 */
@Getter
@Setter
public class LoginRequestContextModel{
    
    @NotNull
    @NotBlank(message = "property 'login' cannot be empty")
    public String login;
    
    @NotNull
    @NotBlank(message = "property 'password' cannot be empty")
    public String password;
}
