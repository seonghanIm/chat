package com.example.chat.domain.chatmessage.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@RequiredArgsConstructor
public class ChatMessageDto implements ChatMessageInterfaceDto{


    private MessageType messageType;

    private String chatRoomId;

    private String senderId;

    private String senderName;

    private String message;

    private String CHAT_ROOM_ADDR = "/sub/chat/room/";

    private String messageTime;


    public String getCHAT_ROOM_ADDR() {
        return CHAT_ROOM_ADDR + chatRoomId;
    }
}
