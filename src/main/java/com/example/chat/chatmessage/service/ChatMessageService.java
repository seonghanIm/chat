package com.example.chat.chatmessage.service;

import com.example.chat.chatmessage.mapper.ChatMessageMapper;
import com.example.chat.chatmessage.model.ChatMessage;
import com.example.chat.chatmessage.model.ChatMessageDto;
import com.example.chat.chatmessage.repository.ChatMessageRepository;
import com.example.chat.chatroom.model.ChatRoom;
import com.example.chat.chatroom.model.ResponseCode;
import com.example.chat.common.model.BaseRequest;
import com.example.chat.common.model.BaseResponse;
import com.example.chat.common.model.Bean;
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
