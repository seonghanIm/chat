package com.example.chat.domain.chatroomuser.model;

import com.example.chat.domain.chatroom.model.ChatRoom;
import com.example.chat.domain.chatroomuser.model.Id.ChatRoomUserId;
import com.example.chat.domain.common.model.BaseEntity;
import com.example.chat.domain.user.model.User;
import jakarta.persistence.*;
import jdk.jfr.BooleanFlag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
@SuperBuilder
public class ChatRoomUser extends BaseEntity {

    @EmbeddedId
    private ChatRoomUserId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("chatRoomId")
    @JoinColumn(name = "chat_room_id")
    private ChatRoom chatRoom;


}
