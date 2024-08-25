package com.example.chat.domain.user.mapper;

import com.example.chat.domain.user.model.User;
import com.example.chat.domain.user.model.UserDto;
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

    public UserDto fromEntity(User u){
        return UserDto.builder()
                .userName(u.getUserName())
                .userId(u.getUserId())
                .build();
    }
}
