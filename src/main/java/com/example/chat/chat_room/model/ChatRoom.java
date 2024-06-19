package com.example.chat.chat_room.model;

import com.example.chat.chat_message.model.ChatMessage;
import com.example.chat.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "chat_room")
public class ChatRoom extends BaseEntity {
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
