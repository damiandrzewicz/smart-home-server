/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.moderndev.smarthome.integration.domain.message.topic;

import com.moderndev.smarthome.integration.message.MessageDirection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

/**
 *
 * @author damian
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopicModel {
    
    @NonNull
    private String domain;
    
    @NonNull
    private String receiverId;
    
    @NonNull
    private String senderId;
    
    @NonNull
    @Builder.Default
    private String delim = "/";

    
}
