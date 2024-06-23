package com.example.chat.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto{
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
