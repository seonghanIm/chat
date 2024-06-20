package com.example.chat.chatmessage.mapper;

import com.example.chat.chatmessage.model.ChatMessage;
import com.example.chat.chatmessage.model.ChatMessageDto;
import com.example.chat.chatroom.repository.ChatRoomRepository;
import com.example.chat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatMessageMapper {
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;

    public ChatMessage toEntity(ChatMessageDto d){
        return ChatMessage.builder()
                .messageType(d.getMessageType())
                .sender(userRepository.findByUserId(d.getSenderId()))
                .message(d.getMessage())
                .chatRoom(chatRoomRepository.findByRoomId(d.getChatRoomId()))
                .build();
    }

}
