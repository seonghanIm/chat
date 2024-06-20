package com.example.chat.user.mapper;

import com.example.chat.user.model.User;
import com.example.chat.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public User toEntity(UserDto d){
        return User.builder()
                .userName(d.getUserName())
                .userId(d.getUserId())
                .password(d.getPassword())
                .build();
    }
}
