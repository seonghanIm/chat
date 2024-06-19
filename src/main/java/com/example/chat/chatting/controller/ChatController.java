package com.example.chat.chatting.controller;

import com.example.chat.chatting.model.ChatMessage;
import com.example.chat.chatting.model.ChatMessageDto;
import com.example.chat.chatting.repository.ChatMessageRepository;
import com.example.chat.chatting.repository.ChatRoomRepository;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ChatController {
    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    @MessageMapping("chat/message")
    public void message(ChatMessageDto requestDto){
        requestDto.setChatRoomRepository(chatRoomRepository);
        chatMessageRepository.save(requestDto.toEntity());
        messagingTemplate.convertAndSend("/sub/chat/room/" + requestDto.getChatRoomId(), requestDto.getMessage());
    }
}
