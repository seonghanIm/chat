package com.example.chat.domain.chatmessage.controller;

import com.example.chat.domain.chatmessage.model.ChatMessageDto;
import com.example.chat.domain.chatmessage.service.ChatMessageService;
import com.example.chat.domain.common.model.BaseRequest;
import com.example.chat.domain.common.model.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ChatMessageController {

    private final ChatMessageService chatMessageService;
    @MessageMapping("chat/message")
    public void message(BaseRequest<ChatMessageDto> req){
        chatMessageService.sendMessage(req.getRequestBody());
    }
}
