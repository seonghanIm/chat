package com.example.chat.domain.chatroom.mapper;

import com.example.chat.domain.chatroom.model.ChatRoom;
import com.example.chat.domain.chatroom.model.ChatRoomDto;
import com.example.chat.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChatRoomMapper {
    private final UserRepository userRepository;
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
                .roomId(d.getRoomId())
                .roomName(d.getRoomName())
                .creator(userRepository.findByUserId(d.getCreatorId()).orElseThrow(()->new UsernameNotFoundException("User not found with user id" + d.getUserId())))
                .build();

    }

}
