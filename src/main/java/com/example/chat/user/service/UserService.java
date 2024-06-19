package com.example.chat.user.service;

import com.example.chat.chat_room.model.ChatRoom;
import com.example.chat.chat_room.model.ChatRoomDto;
import com.example.chat.chat_room.repository.ChatRoomRepository;
import com.example.chat.common.model.BaseRequest;
import com.example.chat.common.model.BaseResponse;
import com.example.chat.user.model.User;
import com.example.chat.user.model.UserDto;
import com.example.chat.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;

    public BaseResponse<UserDto> createUser(BaseRequest<UserDto> req){
        UserDto userDto = req.getRequestBody();
        if (userRepository.existsByUserId(userDto.getUserId())) {
            return BaseResponse.ofFail(400,"이미 존재하는 아이디 입니다.");
        }
        User user = userRepository.save(userDto.toEntity());
        return BaseResponse.ofSuccess(user.fromEntity());
    }

    public BaseResponse<ChatRoomDto> outChatRoom(BaseRequest<UserDto> req){
        UserDto userDto = req.getRequestBody();
        ChatRoom chatRoom = chatRoomRepository.findByRoomId(userDto.getChatRoomId());
        chatRoom.delete();
        User user = userRepository.findByUserId(userDto.getUserId());
        user.outChatRoom(chatRoom);
        return BaseResponse.ofSuccess(chatRoom.fromEntity());
    }
}
