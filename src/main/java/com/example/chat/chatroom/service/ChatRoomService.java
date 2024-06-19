package com.example.chat.chatroom.service;

import com.example.chat.chatroom.model.ChatRoom;
import com.example.chat.chatroom.model.ChatRoomDto;
import com.example.chat.chatroom.repository.ChatRoomRepository;
import com.example.chat.common.model.BaseRequest;
import com.example.chat.common.model.BaseResponse;
import com.example.chat.common.model.Bean;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final Bean bean;

    public BaseResponse<List<ChatRoomDto>> getChatRoomList(BaseRequest<ChatRoomDto> req){
        ChatRoomDto chatRoomDto = req.getRequestBody();
        chatRoomDto.init(bean);
        List<ChatRoom> chatRoomList = chatRoomRepository.findMyChatRoom(chatRoomDto.getUserId());
        List<ChatRoomDto> resList = new ArrayList<>();
        for(ChatRoom chatRoom : chatRoomList){
            resList.add(chatRoom.fromEntity());
        }
        return BaseResponse.ofSuccess(resList);
    }

    public BaseResponse<ChatRoomDto> createRoom(BaseRequest<ChatRoomDto> req){
        ChatRoomDto reqDto = req.getRequestBody();
        reqDto.init(bean);
        ChatRoom chatRoom = reqDto.createRoom();
        return BaseResponse.ofSuccess(chatRoom.fromEntity());
    }
}
