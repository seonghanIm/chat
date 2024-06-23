package com.example.chat.domain.common.model;

import com.example.chat.domain.chatmessage.repository.ChatMessageRepository;
import com.example.chat.domain.chatroom.repository.ChatRoomRepository;
import com.example.chat.domain.user.repository.UserRepository;
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
