package com.example.chat.chatting.repository;

import com.example.chat.chatting.model.ChatRoom;
import jakarta.annotation.PostConstruct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {


    List<ChatRoom> findAll();
    ChatRoom findByRoomId(String roomId);
    ChatRoom save(ChatRoom chatRoom);
}
