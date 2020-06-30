/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.message.common;

import com.moderndev.smarthome.integration.domain.message.State;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author damian
 */
@Setter
@Getter
public class HealthReportRequestContextModel {
    
    private State state;
    
    private String latestError;
}
