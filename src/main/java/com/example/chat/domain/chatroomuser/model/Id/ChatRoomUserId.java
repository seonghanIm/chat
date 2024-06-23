package com.example.chat.domain.chatroomuser.model.Id;

import jakarta.persistence.Embeddable;
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
