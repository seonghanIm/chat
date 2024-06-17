package com.example.chat.chatting.repository;

import com.example.chat.chatting.model.ChatRoom;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;

@Repository
public class ChatRoomRepository {

    private Map<String,ChatRoom> chatRoomMap;

    @PostConstruct
    private void init(){
        chatRoomMap = new LinkedHashMap<>();
    }

    public List<ChatRoom> findAllRoom(){
        List<ChatRoom> chatRoomList = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRoomList);
        return chatRoomList;
    }

    public ChatRoom findByRoomId(String id){
        return chatRoomMap.get(id);
    }

    public ChatRoom createChatRoom(String name){
        ChatRoom chatRoom = ChatRoom.create(name);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }
}
