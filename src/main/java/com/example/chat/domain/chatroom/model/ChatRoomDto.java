package com.example.chat.domain.chatroom.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomDto {
    private String roomName;
    private String roomId;
    private String creatorId;
    private List<String> participantsList;
    private String createdAt;
}
