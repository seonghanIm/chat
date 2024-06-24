package com.example.chat.domain.chatroom.model;

import com.example.chat.domain.chatmessage.model.ChatMessage;
import com.example.chat.domain.chatroomuser.model.ChatRoomUser;
import com.example.chat.domain.chatroomuser.model.Id.ChatRoomUserId;
import com.example.chat.domain.common.model.BaseEntity;
import com.example.chat.domain.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "chat_room")
public class ChatRoom extends BaseEntity {
    @Id
    private String roomId;

    private String roomName;

    @ToString.Exclude
    @OneToMany(mappedBy = "chatRoom")
    private List<ChatMessage> messages;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @ToString.Exclude
    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatRoomUser> participantsList = new ArrayList<>();

    public ChatRoomDto fromEntity(){
        return ChatRoomDto.builder()
                .roomName(this.roomName)
                .participantsList(getParticipantIdList())
                .createdAt(this.getCreatedAt())
                .build();
    }

    public List<String> getParticipantIdList(){
        List<String> participantIdList = new ArrayList<>();
        for(ChatRoomUser chatRoomUser : this.participantsList){
            participantIdList.add(chatRoomUser.getUser().getUserId());
        }
        return participantIdList;
    }

    public void addParticipant(User user){
        ChatRoomUser chatRoomUser = ChatRoomUser.builder()
                .id(new ChatRoomUserId(getRoomId(),user.getUserId()))
                .chatRoom(this)
                .user(user)
                .build();
        this.participantsList.add(chatRoomUser);
    }
}
