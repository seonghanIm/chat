package com.example.chat.common.model;

import com.example.chat.chat_message.model.ChatMessage;
import com.example.chat.chat_message.repository.ChatMessageRepository;
import com.example.chat.chat_room.model.ChatRoom;
import com.example.chat.chat_room.repository.ChatRoomRepository;
import com.example.chat.user.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Data
public class Bean {
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
}
