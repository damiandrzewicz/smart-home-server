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
@Setter
@Getter
public class SenderContextModel {
    
    @NotNull
    @NotBlank(message = "property 'senderId' cannot be empty")
    private String senderId;
}
