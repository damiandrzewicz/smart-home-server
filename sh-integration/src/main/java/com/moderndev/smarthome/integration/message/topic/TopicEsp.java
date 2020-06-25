/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.message.topic;

import com.moderndev.smarthome.integration.domain.message.topic.TopicModel;
import java.util.StringTokenizer;
import lombok.NonNull;


public class TopicEsp implements Topic {
    
    private static final int RequiredItemSize = 3;

    /**
     * Pattern: "{domain}/{receiver_id}/{sender_id}
     * @param tm
     * @return 
     */
    @Override
    public String build(@NonNull TopicModel tm) throws TopicProcessingException {
        
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
    
    @Override
    public TopicModel parse(@NonNull String topic) throws TopicProcessingException{
        
        TopicModel tm = new TopicModel();
        
        StringTokenizer tokenizer = new StringTokenizer(topic, tm.getDelim());
        
        if(tokenizer.countTokens() == RequiredItemSize){
            tm.setDomain(tokenizer.nextToken());
            tm.setReceiverId(tokenizer.nextToken());
            tm.setSenderId(tokenizer.nextToken());
        } else {
            String error = String.format("wrong items quantity: is=[%d], should=[%d]", 
                    tokenizer.countTokens(), 
                    RequiredItemSize);
            throw new TopicProcessingException(error);
        }
       
        return tm;
    }
}
