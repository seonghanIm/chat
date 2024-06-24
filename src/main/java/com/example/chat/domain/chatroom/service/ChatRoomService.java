package com.example.chat.domain.chatroom.service;

import com.example.chat.domain.chatroom.mapper.ChatRoomMapper;
import com.example.chat.domain.chatroom.model.ChatRoom;
import com.example.chat.domain.chatroom.model.ChatRoomDto;
import com.example.chat.domain.chatroom.repository.ChatRoomRepository;
import com.example.chat.domain.chatroomuser.model.ChatRoomUser;
import com.example.chat.domain.chatroomuser.model.Id.ChatRoomUserId;
import com.example.chat.domain.common.model.BaseRequest;
import com.example.chat.domain.common.model.BaseResponse;
import com.example.chat.domain.user.model.User;
import com.example.chat.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomMapper chatRoomMapper;
    private final UserRepository userRepository;

    @Transactional
    public BaseResponse<List<ChatRoomDto>> getChatRoomList(BaseRequest req){
        List<ChatRoom> chatRoomList = chatRoomRepository.findMyChatRoomList(req.getUserId());
        List<ChatRoomDto> resList = new ArrayList<>();
        for(ChatRoom chatRoom : chatRoomList){
            resList.add(chatRoomMapper.fromEntity(chatRoom));
        }
        return BaseResponse.ofSuccess(resList);
    }

    @Transactional
    public BaseResponse<ChatRoomDto> createRoom(BaseRequest<ChatRoomDto> req){
        ChatRoomDto reqDto = req.getRequestBody();
        reqDto.setCreatorId(req.getUserId());
        ChatRoom chatRoom = chatRoomMapper.createRoom(reqDto);

        User user = userRepository.findByUserId(req.getUserId()).orElseThrow(()->
                new UsernameNotFoundException("User not found with user Id" + req.getUserId()));
        chatRoom.addParticipant(user);

        ChatRoom resChatRoom =chatRoomRepository.save(chatRoom);

        return BaseResponse.ofSuccess(resChatRoom.fromEntity());
    }

    @Transactional
    public BaseResponse<ChatRoomDto> joinRoom(BaseRequest<ChatRoomDto> req){
        ChatRoomDto reqDto = req.getRequestBody();
        ChatRoom targetRoom = chatRoomRepository.findByRoomId(reqDto.getRoomId());
        User targetUser = userRepository.findByUserId(req.getUserId()).orElseThrow(()->
                new UsernameNotFoundException("user Id not exist "+ req.getUserId()));
        targetRoom.addParticipant(targetUser);
        return BaseResponse.ofSuccess(targetRoom.fromEntity());
    }

    @Transactional
    public BaseResponse<ChatRoomDto> inviteRoom(BaseRequest<ChatRoomDto> req){
        ChatRoomDto reqDto = req.getRequestBody();
        ChatRoom targetRoom = chatRoomRepository.findByRoomId(reqDto.getRoomId());
        User invitedUser = userRepository.findByUserId(reqDto.getInvitedUserid())
                .orElseThrow(()->new UsernameNotFoundException("user Id not exist" + req.getUserId()));
        targetRoom.addParticipant(invitedUser);
        return BaseResponse.ofSuccess(targetRoom.fromEntity());
    }

}
