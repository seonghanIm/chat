package com.example.chat.chat_message.model;

import com.example.chat.chat_room.model.ChatRoom;
import com.example.chat.common.model.BaseEntity;
import com.example.chat.user.model.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "chat_message")
public class ChatMessage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "message_type", nullable = false)
    private MessageType messageType;

    @ManyToOne
    @JoinColumn(name = "chat_room", nullable = false)
    private ChatRoom chatRoom;

    @ManyToOne
    private User sender;

    private String message;

    private static void saveChatMessage(){

    }

}
