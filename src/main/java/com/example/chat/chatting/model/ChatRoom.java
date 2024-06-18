package com.example.chat.chatting.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "chat_room")
public class ChatRoom {
    @Id
    private String roomId;

    private String name;

    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessage> messages;

    public static ChatRoom create(String name) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setRoomId(UUID.randomUUID().toString());
        chatRoom.setName(name);
        return chatRoom;
    }
}
