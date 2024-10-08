package com.example.chat.domain.chatmessage.mapper;

import com.example.chat.domain.chatmessage.model.ChatMessage;
import com.example.chat.domain.chatmessage.model.ChatMessageDto;
import com.example.chat.domain.chatroom.repository.ChatRoomRepository;
import com.example.chat.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatMessageMapper {
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;

    public ChatMessage toEntity(ChatMessageDto d){
        return ChatMessage.builder()
                .messageType(d.getMessageType())
                .sender(userRepository.findByUserId(d.getSenderId()).orElseThrow(()->new UsernameNotFoundException("User not found with user id" + d.getSenderId())))
                .message(d.getMessage())
                .chatRoom(chatRoomRepository.findByRoomId(d.getChatRoomId()))
                .build();
    }


}
