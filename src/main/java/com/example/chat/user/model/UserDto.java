package com.example.chat.user.model;

import com.example.chat.common.model.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

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
