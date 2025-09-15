package com.chatapp.realtime_chat_service.config;

import com.chatapp.realtime_chat_service.model.ChatMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisSubscriber {

    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public RedisSubscriber(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void onMessage(String messageJson) {
        try {
            ChatMessage chatMessage = objectMapper.readValue(messageJson, ChatMessage.class);
            if(chatMessage.getReceiverId() == null) {
                messagingTemplate.convertAndSend("/topic/public", chatMessage);
            } else {
                messagingTemplate.convertAndSend("/queue/" + chatMessage.getReceiverId(), chatMessage);
            }
        }
        catch(Exception e){
            throw new RuntimeException("Erreur lors de la conversion JSON en ChatMessage", e);
        }
    }
}
