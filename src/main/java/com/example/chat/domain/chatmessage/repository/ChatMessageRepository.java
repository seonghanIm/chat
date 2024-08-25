package com.example.chat.domain.chatmessage.repository;

import com.example.chat.domain.chatmessage.model.ChatMessage;
import com.example.chat.domain.chatmessage.model.ChatMessageInterfaceDto;
import com.example.chat.domain.chatmessage.model.MessageType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage,Long> {
    ChatMessage save(ChatMessage chatMessage);
    @Query(value = "select chat_room as chatRoomId , message, sender_user_id as senderId, created_at as messageTime from chat_message where created_at between :startDate and :endDate and chat_room = :chatRoomId and message_type = :messageType order by created_at", nativeQuery = true)
    List<ChatMessageInterfaceDto> findChatMessageByChatRoom(@Param("chatRoomId") String chatRoomId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate, @Param("messageType") String messageType);
}
