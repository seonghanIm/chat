package com.example.chat.domain.chatroom.repository;

import com.example.chat.domain.chatroom.model.ChatRoom;
import com.example.chat.domain.chatroom.model.ChatRoomDto;
import com.example.chat.domain.chatroom.model.ChatRoomInterfaceDto;
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

    @Query(value = "SELECT cr.room_id AS roomId, cr.room_name AS roomName, cr.creator_id AS creatorId, cr.created_at AS createdAt \n" +
            "                   FROM chat_room AS cr  \n" +
            "                   JOIN chat_room_user AS cru ON cr.room_id = cru.chat_room_id  \n" +
            "                   WHERE cru.user_id = :userId  \n" +
            "                   AND cr.delete_yn = 'N';",nativeQuery = true)
    List<ChatRoomInterfaceDto> findMyChatRoomList(@Param("userId") String userId);
}
