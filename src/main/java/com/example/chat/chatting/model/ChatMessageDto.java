package com.example.chat.chatting.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChatMessageDto {

    private MessageType messageType;

    private String chatRoomId;

    private String sender;

    private String message;

}
