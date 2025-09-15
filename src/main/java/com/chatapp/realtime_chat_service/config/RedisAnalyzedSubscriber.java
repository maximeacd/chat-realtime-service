package com.chatapp.realtime_chat_service.config;

import com.chatapp.realtime_chat_service.model.ChatMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisAnalyzedSubscriber {

    private final SimpMessagingTemplate messagingTemplate;

    public RedisAnalyzedSubscriber(SimpMessagingTemplate messagingTemplate){
        this.messagingTemplate=messagingTemplate;
    }

    public void onMessage(ChatMessage analyzedMessage){
        if(analyzedMessage.getReceiverId()==null){
            messagingTemplate.convertAndSend("/topic/analyzed", analyzedMessage);
        }
        else{
            messagingTemplate.convertAndSend("/queue/analyzed/"+analyzedMessage.getReceiverId());
        }
    }
}
