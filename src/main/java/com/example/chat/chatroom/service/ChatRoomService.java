package com.example.chat.chatroom.service;

import com.example.chat.chatroom.mapper.ChatRoomMapper;
import com.example.chat.chatroom.model.ChatRoom;
import com.example.chat.chatroom.model.ChatRoomDto;
import com.example.chat.chatroom.repository.ChatRoomRepository;
import com.example.chat.chatroomuser.model.ChatRoomUser;
import com.example.chat.chatroomuser.model.Id.ChatRoomUserId;
import com.example.chat.common.model.BaseResponse;
import com.example.chat.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMapper chatRoomMapper;

    public BaseResponse<List<ChatRoomDto>> getChatRoomList(ChatRoomDto reqDto){
        List<ChatRoom> chatRoomList = chatRoomRepository.findMyChatRoom(reqDto.getUserId());
        List<ChatRoomDto> resList = new ArrayList<>();
        for(ChatRoom chatRoom : chatRoomList){
            resList.add(chatRoomMapper.fromEntity(chatRoom));
        }
        return BaseResponse.ofSuccess(resList);
    }

    public BaseResponse<ChatRoomDto> createRoom(ChatRoomDto reqDto){
        ChatRoom chatRoom = chatRoomMapper.toEntity(reqDto);
        chatRoomRepository.save(chatRoom);
        return BaseResponse.ofSuccess(chatRoom.fromEntity());
    }

    public void addUserToChatRoom(ChatRoom chatRoom, String userId){
        List<ChatRoomUser> list =  chatRoom.getParticipantsList();
        ChatRoomUserId chatRoomUserId = ChatRoomUserId.
                builder().userId(userId).chatRoomId(chatRoom.getRoomId())
                .build();

    }
}
