package com.example.chat.chatroomuser.model;

import com.example.chat.chatroom.model.ChatRoom;
import com.example.chat.chatroomuser.model.Id.ChatRoomUserId;
import com.example.chat.common.model.BaseEntity;
import com.example.chat.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Entity
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
