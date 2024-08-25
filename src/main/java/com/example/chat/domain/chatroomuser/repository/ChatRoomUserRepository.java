package com.example.chat.domain.chatroomuser.repository;

import com.example.chat.domain.chatroomuser.model.ChatRoomUser;
import com.example.chat.domain.chatroomuser.model.Id.ChatRoomUserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUser, ChatRoomUserId> {
    Optional<List<ChatRoomUser>> findByIdChatRoomId(String chatRoomId);
    Optional<List<ChatRoomUser>> findByIdUserId(String userId);
}
