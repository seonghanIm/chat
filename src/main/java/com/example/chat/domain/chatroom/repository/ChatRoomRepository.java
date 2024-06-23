package com.example.chat.domain.chatroom.repository;

import com.example.chat.domain.chatroom.model.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, String> {

    List<ChatRoom> findAll();
    ChatRoom findByRoomId(String roomId);
    ChatRoom save(ChatRoom chatRoom);
    List<ChatRoom> findAllByDeleteYnEquals(String deleteYn);
    @Query(value = "select chat_room_id\n" +
            "from chat_room as cr\n" +
            "join chat_room_user as cru on cr.room_id = cru.chat_room_id\n" +
            "where cru.user_id = :userId\n" +
            "and cr.delete_yn = 'N'",nativeQuery = true)
    List<ChatRoom> findMyChatRoom(@Param("userId") String userId);
}
