package com.example.chat.chat_message.service;

import com.example.chat.chat_message.model.ChatMessageDto;
import com.example.chat.chat_message.repository.ChatMessageRepository;
import com.example.chat.chat_room.repository.ChatRoomRepository;
import com.example.chat.common.model.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    public BaseResponse sendMessage(ChatMessageDto requestDto){
        requestDto.setChatRoomRepository(chatRoomRepository);
        chatMessageRepository.save(requestDto.toEntity());
        messagingTemplate.convertAndSend("/sub/chat/room/" + requestDto.getChatRoomId(), requestDto.getMessage());
        return new BaseResponse(200,"SUCCESS");
    }

}
