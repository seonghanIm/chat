package com.example.chat.user.model;

import com.example.chat.chat_message.model.ChatMessage;
import com.example.chat.chat_room.model.ChatRoom;
import com.example.chat.common.model.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends BaseDto {
    private String userId;
    private String userName;
    private String password;
    private String chatRoomId;

    public User toEntity(){
        return User.builder()
                .userName(this.getUserName())
                .userId(this.getUserId())
                .password(this.getPassword())
                .build();
    }
}
