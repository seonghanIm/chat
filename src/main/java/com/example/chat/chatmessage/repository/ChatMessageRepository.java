package com.example.chat.chatmessage.repository;

import com.example.chat.chatmessage.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {
    ChatMessage save(ChatMessage chatMessage);

}
