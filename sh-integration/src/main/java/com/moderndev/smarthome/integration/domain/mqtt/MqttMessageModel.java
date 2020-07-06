/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.mqtt;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
public class MqttMessageModel {
    
    @NotNull(message = "property 'topic' cannot be null")
    @NotBlank(message = "property 'topic' cannot be blanc")
    private String topic;
    
    @NotNull(message = "property 'payload' cannot be null")
    @NotBlank(message = "property 'payload' cannot be blanc")
    private String payload;

    @Min(value = 0, message = "property 'qos' cannot be lover than 0")
    @Max(value = 2, message = "property 'qos' cannot be higher than 2")
    private int qos = 0;
}
