/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message.topic;

import com.moderndev.smarthome.integration.domain.message.topic.TopicModel;
import lombok.NonNull;


public class TopicBuilderMqtt implements TopicBuilder {

    /**
     * Pattern: "{domain}/{receiver_id}/{direction}/{sender_id}
     * @param tm
     * @return 
     */
    @Override
    public String build(@NonNull TopicModel tm) throws TopicBuilderException {
        
        StringBuilder sb = new StringBuilder();

        //append domain
        sb.append(tm.getDomain());

        //append receiver_id
        sb.append(tm.getDelim());
        sb.append(tm.getReceiverId());
        
        sb.append(tm.getDelim());
        sb.append(tm.getSenderId());

        return sb.toString();
    }
    
}
