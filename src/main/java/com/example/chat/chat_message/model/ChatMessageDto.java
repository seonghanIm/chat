package com.example.chat.chat_message.model;

import com.example.chat.chat_room.repository.ChatRoomRepository;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChatMessageDto {
    public ChatRoomRepository chatRoomRepository;
    public ChatMessageDto(ChatRoomRepository chatRoomRepository){
        this.chatRoomRepository = chatRoomRepository;
    }
    private MessageType messageType;

    private String chatRoomId;

    private String sender;

    private String message;

    public ChatMessage toEntity(){
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMessageType(messageType);
        chatMessage.setSender(sender);
        chatMessage.setMessage(message);
        chatMessage.setChatRoom(chatRoomRepository.findByRoomId(chatRoomId));
        return chatMessage;
    }

}
