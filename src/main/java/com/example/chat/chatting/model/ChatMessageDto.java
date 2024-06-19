package com.example.chat.chatting.model;

import com.example.chat.chatting.repository.ChatMessageRepository;
import com.example.chat.chatting.repository.ChatRoomRepository;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ChatMessageDto {
    public ChatRoomRepository chatRoomRepository;
    public ChatMessageDto(ChatRoomRepository chatRoomRepository){
        this.chatRoomRepository = chatRoomRepository;
    }
    private MessageType messageType;

    private String chatRoomId;

    private String sender;

    private String message;

    public ChatMessage toEntity(){
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessageType(messageType);
        chatMessage.setSender(sender);
        chatMessage.setMessage(message);
        chatMessage.setChatRoom(chatRoomRepository.findByRoomId(chatRoomId));
        return chatMessage;
    }

}
