package com.example.chat.chatroom.model;

import com.example.chat.common.model.BaseDto;
import com.example.chat.user.model.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.UUID;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDto extends BaseDto {
    private String roomName;
    private String creatorId;
    private String userId;
    private List<String> userIdList;
    private String createdAt;

    public ChatRoom toEntity(){
        return ChatRoom.builder()
                .roomId(UUID.randomUUID().toString())
                .roomName(this.roomName)
                .creator(userRepository.findByUserId(creatorId))
                .build();

    }

    public ChatRoom createRoom(){
        return chatRoomRepository.save(toEntity());
    }
}
