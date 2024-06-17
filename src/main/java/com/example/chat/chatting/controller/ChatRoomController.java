package com.example.chat.chatting.controller;

import com.example.chat.chatting.model.ChatRoom;
import com.example.chat.chatting.model.ChatRoomDto;
import com.example.chat.chatting.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;

    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room(){
        return chatRoomRepository.findAllRoom();
    }

    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestBody ChatRoomDto dto){
        return chatRoomRepository.createChatRoom(dto.getRoomName());
    }

    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId){
        return chatRoomRepository.findByRoomId(roomId);
    }





}
