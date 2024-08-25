package com.example.chat.domain.chatmessage.controller;

import com.example.chat.domain.chatmessage.model.ChatMessageDto;
import com.example.chat.domain.chatmessage.model.ChatMessageReqDto;
import com.example.chat.domain.chatmessage.service.ChatMessageService;
import com.example.chat.domain.chatroom.model.ResponseCode;
import com.example.chat.domain.common.model.BaseRequest;
import com.example.chat.domain.common.model.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ChatMessageController {

    private final ChatMessageService chatMessageService;
    @MessageMapping("chat/message")
    public void message(BaseRequest<ChatMessageDto> req){
        chatMessageService.sendMessage(req.getRequestBody());
    }

    @PostMapping("/messages")
    public BaseResponse<List<ChatMessageDto>> getChatMessageList(@RequestBody BaseRequest<ChatMessageReqDto> request, BindingResult result){
        if(result.hasErrors()){
            BaseResponse.ofFail(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getMessage());
        }
        return chatMessageService.getChatMessageList(request.getRequestBody());
    }
}
