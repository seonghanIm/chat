package com.example.chat.chat_room.model;

import com.example.chat.common.model.BaseDto;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import net.bytebuddy.implementation.bind.annotation.Super;

@Data
@SuperBuilder
public class ChatRoomDto extends BaseDto {
    String roomName;
}
