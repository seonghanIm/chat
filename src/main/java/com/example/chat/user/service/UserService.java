package com.example.chat.user.service;

import com.example.chat.chatroom.model.ChatRoom;
import com.example.chat.chatroom.model.ChatRoomDto;
import com.example.chat.chatroom.repository.ChatRoomRepository;
import com.example.chat.common.model.BaseRequest;
import com.example.chat.common.model.BaseResponse;
import com.example.chat.user.model.User;
import com.example.chat.user.model.UserDto;
import com.example.chat.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public BaseResponse<UserDto> createUser(UserDto reqDto){
        if (userRepository.existsByUserId(reqDto.getUserId())) {
            return BaseResponse.ofFail(400,"이미 존재하는 아이디 입니다.");
        }
        User user = userRepository.save(reqDto.toEntity());
        return BaseResponse.ofSuccess(user.fromEntity());
    }

    @Transactional
    public BaseResponse<ChatRoomDto> outChatRoom(UserDto reqDto){
        ChatRoom chatRoom = chatRoomRepository.findByRoomId(reqDto.getChatRoomId());
        User user = userRepository.findByUserId(reqDto.getUserId())
                .orElseThrow(()->new UsernameNotFoundException("User not found with user Id" + reqDto.getUserId()));

        user.outChatRoom(chatRoom);
        return BaseResponse.ofSuccess(chatRoom.fromEntity());
    }
}
