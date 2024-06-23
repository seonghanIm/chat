package com.example.chat.domain.chatmessage.service;

import com.example.chat.domain.chatmessage.mapper.ChatMessageMapper;
import com.example.chat.domain.chatmessage.model.ChatMessage;
import com.example.chat.domain.chatmessage.model.ChatMessageDto;
import com.example.chat.domain.chatmessage.repository.ChatMessageRepository;
import com.example.chat.domain.common.model.BaseResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageMapper chatMessageMapper;

    @Transactional
    public BaseResponse sendMessage(ChatMessageDto reqDto){
        ChatMessage res = chatMessageRepository.save(chatMessageMapper.toEntity(reqDto));
        messagingTemplate.convertAndSend("/sub/chat/room/" + reqDto.getChatRoomId(), reqDto.getMessage());
        return BaseResponse.ofSuccess(res);
    }



}
