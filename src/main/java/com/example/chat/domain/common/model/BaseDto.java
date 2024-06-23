package com.example.chat.domain.common.model;

import com.example.chat.domain.chatroom.repository.ChatRoomRepository;
import com.example.chat.domain.user.repository.UserRepository;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseDto {
    @Schema(hidden = true)
    public ChatRoomRepository chatRoomRepository;

    @Schema(hidden = true)
    public UserRepository userRepository;


    public void init(Bean bean){
        this.chatRoomRepository = bean.getChatRoomRepository();
        this.userRepository = bean.getUserRepository();
    }



}
