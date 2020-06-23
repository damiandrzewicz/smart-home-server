/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.mqtt;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 *
 * @author damian
 */
@Setter
@Getter
@Builder
@NoArgsConstructor
public class MqttMessageModel {
    
    @NonNull
    private String topic;
    
    @NonNull
    private String payload;

    @Builder.Default
    private int qos = 0;
}
