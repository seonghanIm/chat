package com.example.chat.domain.chatmessage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class ChatMessageDto {

    public ChatMessageDto(){
        this.CHAT_ROOM_ADDR += this.chatRoomId;
    }

    private MessageType messageType;

    private String chatRoomId;

    private String senderId;

    private String message;

    private String CHAT_ROOM_ADDR = "/sub/chat/room/";


}
