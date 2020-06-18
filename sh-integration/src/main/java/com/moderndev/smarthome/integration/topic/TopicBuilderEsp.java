/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.topic;

import com.moderndev.smarthome.integration.domain.topic.TopicModel;
import lombok.NonNull;


public class TopicBuilderEsp implements TopicBuilder {

    /**
     * Pattern: "{domain}/{receiver_id}/{direction}/{sender_id}
     * @param tm
     * @return 
     */
    @Override
    public String build(@NonNull TopicModel tm) {
        
        StringBuilder sb = new StringBuilder();

        //append domain
        sb.append(tm.getDomain());

        //append receiver_id
        sb.append(tm.getDelim());
        sb.append(tm.getReceiverId());

        //append direction
        sb.append(tm.getDelim());
        sb.append(tm.getDirection().getString());
        
        sb.append(tm.getDelim());
        sb.append(tm.getSenderId());

        return sb.toString();
    }
    
}
