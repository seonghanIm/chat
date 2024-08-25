package com.example.chat.domain.chatmessage.model;

public interface ChatMessageInterfaceDto {
    String getChatRoomId();
    String getSenderId();
    String getMessage();
    String getMessageTime();
}
