package com.example.chat.domain.chatroom.controller;

import com.example.chat.domain.chatroom.model.ChatRoom;
import com.example.chat.domain.chatroom.model.ChatRoomDto;
import com.example.chat.domain.chatroom.repository.ChatRoomRepository;
import com.example.chat.domain.chatroom.service.ChatRoomService;
import com.example.chat.domain.common.model.BaseRequest;
import com.example.chat.domain.common.model.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat/room")
@RequiredArgsConstructor
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomService chatRoomService;

    @PostMapping("/list")
    @ResponseBody
    public BaseResponse<List<ChatRoomDto>> room(@RequestBody BaseRequest<ChatRoomDto> req, BindingResult result){
        if(result.hasErrors()){
            BaseResponse.ofFail(400,result.toString());
        }
        return chatRoomService.getChatRoomList(req);
    }

    @PostMapping("/create")
    public BaseResponse<ChatRoomDto> createRoom(@RequestBody BaseRequest<ChatRoomDto> req,BindingResult result){
        if(result.hasErrors()){
            BaseResponse.ofFail(400,result.toString());
        }
        return chatRoomService.createRoom(req);
    }


    @PostMapping("/join")
    public BaseResponse<ChatRoomDto> joinRoom(@RequestBody BaseRequest<ChatRoomDto> req, BindingResult result){
        if(result.hasErrors()){
            BaseResponse.ofFail(400,result.toString());
        }
        return chatRoomService.joinRoom(req);
    }

    @PostMapping("/invite")
    public BaseResponse<ChatRoomDto> inviteRoom(@RequestBody BaseRequest<ChatRoomDto> req, BindingResult result){
        if(result.hasErrors()){
            BaseResponse.ofFail(400, result.toString());
        }
        return chatRoomService.inviteRoom(req);
    }


    @GetMapping("/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId){
        return chatRoomRepository.findByRoomId(roomId);
    }






}
