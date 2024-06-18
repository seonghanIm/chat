package com.example.chat.chatting.controller;

import com.example.chat.chatting.model.ChatMessage;
import com.example.chat.chatting.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Controller
public class ChatController {
    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatRoomRepository chatRoomRepository;

    @MessageMapping("chat/message")
    public void message(ChatMessage message){
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getChatRoom().getRoomId(), message);
    }
}
