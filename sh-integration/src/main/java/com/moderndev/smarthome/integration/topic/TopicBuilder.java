/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.topic;

import com.moderndev.smarthome.integration.domain.topic.TopicModel;

/**
 *
 * @author damian
 */
public interface TopicBuilder{
    
    String build(TopicModel tm);
}
