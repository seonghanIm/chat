package com.example.chat.chatroom.mapper;

import com.example.chat.chatroom.model.ChatRoom;
import com.example.chat.chatroom.model.ChatRoomDto;
import com.example.chat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
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
                .creator(userRepository.findByUserId(d.getCreatorId()))
                .build();

    }

}
