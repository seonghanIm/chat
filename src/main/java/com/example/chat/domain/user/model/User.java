package com.example.chat.domain.user.model;

import com.example.chat.domain.chatmessage.model.ChatMessage;
import com.example.chat.domain.chatroom.model.ChatRoom;
import com.example.chat.domain.chatroomuser.model.ChatRoomUser;
import com.example.chat.domain.common.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

// mapped by 는 foreign key 를 가지고 있지 않은 쪽에 달아준다.
// 관계의 주인이 아닌쪽에 달아준다.
// User는 ChatRoom 이 없어도 의미 상 하나의 객체로서 존재가능하지만,
// ChatRoom 은 User 가 없다면 의미가 없는 객체이다.
@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_user")
public class User extends BaseEntity {
    @Id
    @Column(name = "user_id", nullable = false)
    private String userId;

    @ToString.Exclude
    @OneToMany(mappedBy =  "creator")
    private List<ChatRoom> chatRoomList;

    @OneToMany(mappedBy = "sender")
    private List<ChatMessage> messageList;

    @Column(name = "user_name", nullable = false)
    private String  userName;

    @Column(nullable = false)
    private String password;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatRoomUser> chatRoomUserList = new ArrayList<>();


    public void changeUserId(String userId){
        this.userId = userId;
    }

    public ChatRoom outChatRoom(ChatRoom chatRoom){
        this.chatRoomList.remove(chatRoom);
        return chatRoom;
    }

    public UserDto fromEntity(){
        return UserDto.builder()
                .userId(this.userId)
                .userName(this.userName)
                .build();
    }

}
