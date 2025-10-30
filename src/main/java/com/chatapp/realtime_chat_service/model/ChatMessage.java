package com.chatapp.realtime_chat_service.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatMessage {

    private Long senderId;
    private Long receiverId;
    private String senderName;
    private String content;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static ChatMessage fromJson(String json){
        try{
            return objectMapper.readValue(json, ChatMessage.class);
        }
        catch(Exception e){
            throw new RuntimeException("Erreur lors de la conversion JSON en chatMessge", e);
        }
    }

    public String toJson(){
        try{
            return objectMapper.writeValueAsString(this);
        }
        catch(Exception e){
            throw new RuntimeException("Erreur lors de la conversion chatMessage en JSON",e);
        }
    }
}
