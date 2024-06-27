package com.example.chat.domain.chatmessage.service;

import com.example.chat.domain.chatmessage.mapper.ChatMessageMapper;
import com.example.chat.domain.chatmessage.model.ChatMessage;
import com.example.chat.domain.chatmessage.model.ChatMessageDto;
import com.example.chat.domain.chatmessage.model.MessageType;
import com.example.chat.domain.chatmessage.repository.ChatMessageRepository;
import com.example.chat.domain.common.model.BaseResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageMapper chatMessageMapper;

    @Transactional
    public void sendMessage(ChatMessageDto reqDto) {
        chatMessageRepository.save(chatMessageMapper.toEntity(reqDto));

        ChatMessageDto defaultMessageDto = new ChatMessageDto();

        if (reqDto.getMessageType().equals(MessageType.TALK)) {
            messagingTemplate.convertAndSend(reqDto.getCHAT_ROOM_ADDR(), reqDto);
        }
        if (reqDto.getMessageType().equals(MessageType.ENTER)) {
            defaultMessageDto.setMessage(reqDto.getSenderId() + MessageType.ENTER.getDefaultMessage());
            messagingTemplate.convertAndSend(reqDto.getCHAT_ROOM_ADDR(), defaultMessageDto);
            messagingTemplate.convertAndSend(reqDto.getCHAT_ROOM_ADDR(), reqDto);
        }
        if(reqDto.getMessageType().equals(MessageType.EXIT)){
            defaultMessageDto.setMessage(reqDto.getSenderId() + MessageType.EXIT.getDefaultMessage());
            messagingTemplate.convertAndSend(reqDto.getCHAT_ROOM_ADDR(), defaultMessageDto);
        }
    }



}
