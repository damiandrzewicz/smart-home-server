/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.topic;

import com.moderndev.smarthome.integration.domain.topic.TopicDirection;
import com.moderndev.smarthome.integration.domain.topic.TopicModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import lombok.NonNull;


public class TopicParserEsp implements TopicParser {
    
    private static final int RequiredItemSize = 4;

    @Override
    public TopicModel parse(@NonNull String topic) throws TopicParseException{
        
        TopicModel tm = new TopicModel();
        
        StringTokenizer tokenizer = new StringTokenizer(topic, tm.getDelim());
        
        if(tokenizer.countTokens() == RequiredItemSize){
            tm.setDomain(tokenizer.nextToken());
            tm.setReceiverId(tokenizer.nextToken());
            tm.setDirection(TopicDirection.parse(tokenizer.nextToken()));
            tm.setSenderId(tokenizer.nextToken());
        } else {
            String error = String.format("wrong items quantity: is=[%d], should=[%d]", 
                    tokenizer.countTokens(), 
                    RequiredItemSize);
            throw new TopicParseException(error);
        }
       
        return tm;
    }
    
}
