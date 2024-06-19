package com.example.chat.chat_room.repository;

import com.example.chat.chat_room.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {

    List<ChatRoom> findAll();
    ChatRoom findByRoomId(String roomId);
    ChatRoom save(ChatRoom chatRoom);
}
