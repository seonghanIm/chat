package com.example.chat.chatmessage.model;

import com.example.chat.common.model.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class ChatMessageDto extends BaseDto {

    private MessageType messageType;

    private String chatRoomId;

    private String senderId;

    private String message;

    public ChatMessage toEntity(){
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessageType(messageType);
        chatMessage.setSender(getUserRepository().findByUserId(senderId));
        chatMessage.setMessage(message);
        chatMessage.setChatRoom(getChatRoomRepository().findByRoomId(chatRoomId));
        return chatMessage;
    }



}
