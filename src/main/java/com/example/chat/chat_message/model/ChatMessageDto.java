package com.example.chat.chat_message.model;

import com.example.chat.chat_room.repository.ChatRoomRepository;
import com.example.chat.common.model.BaseDto;
import com.example.chat.common.model.Bean;
import com.example.chat.user.repository.UserRepository;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

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
