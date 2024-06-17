package com.example.chat.chatting.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatMessage {

    public enum MessageType{
        ENTER,TALK,EXIT,MATCH,MATCH_REQUEST;
    }

    private MessageType messageType;
    private String roomId;
    private String sender;
    private String message;
}
