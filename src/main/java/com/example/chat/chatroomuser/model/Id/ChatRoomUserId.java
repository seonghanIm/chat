package com.example.chat.chatroomuser.model.Id;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Embeddable
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ChatRoomUserId implements Serializable {
    private String chatRoomId;
    private String userId;
}
