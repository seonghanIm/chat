package com.example.chat.chatroomuser.model.Id;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;

import java.io.Serializable;

@Embeddable
public class ChatRoomUserId implements Serializable {
    private String chatRoomId;
    private String userId;
}
