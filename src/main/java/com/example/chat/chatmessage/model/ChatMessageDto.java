package com.example.chat.chatmessage.model;

import com.example.chat.common.model.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class ChatMessageDto {

    private MessageType messageType;

    private String chatRoomId;

    private String senderId;

    private String message;

}
