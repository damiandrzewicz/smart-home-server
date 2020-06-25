/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message.topic;

import com.moderndev.smarthome.integration.domain.message.topic.TopicModel;

/**
 *
 * @author damian
 */
public interface Topic{
    
    String build(TopicModel tm) throws TopicProcessingException;
    
    TopicModel parse(String topic) throws TopicProcessingException;
}
