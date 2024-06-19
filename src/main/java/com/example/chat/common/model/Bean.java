package com.example.chat.common.model;

import com.example.chat.chatmessage.repository.ChatMessageRepository;
import com.example.chat.chatroom.repository.ChatRoomRepository;
import com.example.chat.user.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Data
public class Bean {
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
}
