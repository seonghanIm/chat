package com.example.chat.chat_message.service;

import com.example.chat.chat_message.model.ChatMessageDto;
import com.example.chat.chat_message.repository.ChatMessageRepository;
import com.example.chat.chat_room.repository.ChatRoomRepository;
import com.example.chat.common.model.BaseRequest;
import com.example.chat.common.model.BaseResponse;
import com.example.chat.common.model.Bean;
import com.example.chat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatMessageRepository chatMessageRepository;
    private final Bean bean;

    public BaseResponse sendMessage(BaseRequest<ChatMessageDto> req){
        ChatMessageDto chatMessageDto = req.getRequestBody();
        chatMessageDto.init(bean);
        chatMessageRepository.save(chatMessageDto.toEntity());
        messagingTemplate.convertAndSend("/sub/chat/room/" + chatMessageDto.getChatRoomId(), chatMessageDto.getMessage());
        return BaseResponse.ofSuccess(chatMessageDto);
    }



}
