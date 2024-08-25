package com.example.chat.domain.chatroom.mapper;

import com.example.chat.domain.chatroom.model.ChatRoom;
import com.example.chat.domain.chatroom.model.ChatRoomDto;
import com.example.chat.domain.chatroom.model.ChatRoomInterfaceDto;
import com.example.chat.domain.chatroomuser.model.ChatRoomUser;
import com.example.chat.domain.chatroomuser.repository.ChatRoomUserRepository;
import com.example.chat.domain.user.model.User;
import com.example.chat.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ChatRoomMapper {
    private final UserRepository userRepository;
    private final ChatRoomUserRepository chatRoomUserRepository;
    public ChatRoomDto fromEntity(ChatRoom e){
        return ChatRoomDto.builder()
                .roomName(e.getRoomName())
                .creatorId(e.getCreator().getUserId())
                .participantsList(e.getParticipantIdList())
                .createdAt(e.getCreatedAt())
                .build();
    }

    public ChatRoom toEntity(ChatRoomDto d){
        return ChatRoom.builder()
                .roomId(UUID.randomUUID().toString())
                .roomName(d.getRoomName())
                .creator(userRepository.findByUserId(d.getCreatorId()).orElseThrow(()->new UsernameNotFoundException("User not found with user id" + d.getCreatorId())))
                .build();

    }

    public ChatRoom createRoom(ChatRoomDto d){
        User creator = userRepository.findByUserId(d.getCreatorId()).orElseThrow(()->new UsernameNotFoundException("User not found with user id" + d.getCreatorId()));
        return ChatRoom.builder()
                .roomId(UUID.randomUUID().toString())
                .roomName(d.getRoomName())
                .creator(creator)
                .participantsList(new ArrayList<>())
                .build();
    }

    public ChatRoomDto fromInterfaceToDto(ChatRoomInterfaceDto interfaceDto){
        return ChatRoomDto.builder()
                .roomId(interfaceDto.getRoomId())
                .roomName(interfaceDto.getRoomName())
                .creatorId(interfaceDto.getCreatorId())
                .createdAt(interfaceDto.getCreatedAt())
                .build();
    }

}
