package com.example.chat.chatting.model;

import com.example.chat.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

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

    private String sender;

    private String message;

    private static void saveChatMessage(){

    }

}
