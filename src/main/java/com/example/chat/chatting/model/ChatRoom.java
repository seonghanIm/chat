package com.example.chat.chatting.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Data
@Builder
public class ChatRoom {
    private String roomId;
    private String name;

    public static ChatRoom create(String name) {
        return ChatRoom.builder()
                .name(name)
                .roomId(UUID.randomUUID().toString()).build();
    }
}
