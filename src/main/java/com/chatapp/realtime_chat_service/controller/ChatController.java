package com.chatapp.realtime_chat_service.controller;

import com.chatapp.realtime_chat_service.entity.Message;
import com.chatapp.realtime_chat_service.model.ChatMessage;
import com.chatapp.realtime_chat_service.repository.MessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final RedisTemplate<String, String> redisTemplate;
    private final ChannelTopic topic;
    private final MessageRepository messageRepository;
    private final ObjectMapper objectMapper= new ObjectMapper();

    @MessageMapping("/chat.sendMessage")
    public void sendMessage(@Payload ChatMessage chatMessage){

        Message message = Message.builder()
                .senderId(chatMessage.getSenderId())
                .receiverId(chatMessage.getReceiverId())
                .content(chatMessage.getContent())
                .timestamp(LocalDateTime.now())
                .build();
        messageRepository.save(message);

        try {
            String json = objectMapper.writeValueAsString(chatMessage);
            redisTemplate.convertAndSend(topic.getTopic(),json);
        }
        catch(Exception e){
            throw new RuntimeException("Erreur de sérialization JSON", e);
        }
    }
}
