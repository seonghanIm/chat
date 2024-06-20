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
public class ChatRoomDto {
    private String roomName;
    private String roomId;
    private String creatorId;
    private String userId;
    private List<String> participantsList;
    private String createdAt;
}
