package com.example.chat.domain.chatmessage.service;

import com.example.chat.domain.chatmessage.mapper.ChatMessageMapper;
import com.example.chat.domain.chatmessage.model.*;
import com.example.chat.domain.chatmessage.repository.ChatMessageRepository;
import com.example.chat.domain.chatroom.model.ChatRoomDto;
import com.example.chat.domain.chatroom.model.ResponseCode;
import com.example.chat.domain.common.model.BaseResponse;
import com.example.chat.domain.user.model.User;
import com.example.chat.domain.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatMessageService {

    private final SimpMessageSendingOperations messagingTemplate;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatMessageMapper chatMessageMapper;
    private final UserRepository userRepository;

    @Transactional
    public void sendMessage(ChatMessageDto reqDto) {
        chatMessageRepository.save(chatMessageMapper.toEntity(reqDto));

        ChatMessageDto defaultMessageDto = new ChatMessageDto();
        User user = userRepository.findByUserId(reqDto.getSenderId()).orElseThrow();
        reqDto.setSenderName(user.getUserName());
        reqDto.setMessageTime(localDateTimeToString(LocalDateTime.now()));
        if (reqDto.getMessageType().equals(MessageType.TALK)) {
            messagingTemplate.convertAndSend(reqDto.getCHAT_ROOM_ADDR(), reqDto);
        }
        if (reqDto.getMessageType().equals(MessageType.ENTER)) {
            defaultMessageDto.setMessage(reqDto.getSenderId() + MessageType.ENTER.getDefaultMessage());
            messagingTemplate.convertAndSend(reqDto.getCHAT_ROOM_ADDR(), defaultMessageDto);
            messagingTemplate.convertAndSend(reqDto.getCHAT_ROOM_ADDR(), reqDto);
        }
        if(reqDto.getMessageType().equals(MessageType.EXIT)){
            defaultMessageDto.setMessage(reqDto.getSenderId() + MessageType.EXIT.getDefaultMessage());
            messagingTemplate.convertAndSend(reqDto.getCHAT_ROOM_ADDR(), defaultMessageDto);
        }
    }

    @Transactional
    public BaseResponse<List<ChatMessageDto>> getChatMessageList(ChatMessageReqDto reqDto){
        try {
            List<ChatMessageInterfaceDto> chatMessageList = chatMessageRepository.findChatMessageByChatRoom(reqDto.getRoomId(),stringToLocalDateTime(reqDto.getStartDate()).minusDays(7),stringToLocalDateTime(reqDto.getStartDate()),reqDto.getMessageType().toString());
            List<ChatMessageDto> resList = new ArrayList<>();
            for(ChatMessageInterfaceDto d : chatMessageList){
                ChatMessageDto dto = new ChatMessageDto();
                dto.setChatRoomId(d.getChatRoomId());
                dto.setMessage(d.getMessage());
                dto.setSenderId(d.getSenderId());
                dto.setMessageTime(d.getMessageTime());
                User sender = userRepository.findByUserId(d.getSenderId()).orElseThrow(()->new UsernameNotFoundException("User not found with user id"));
                dto.setSenderName(sender.getUserName());
                resList.add(dto);
            }
            return BaseResponse.ofSuccess(resList);

        }catch (Exception e){
            log.error("getChatMessageList :: error :: {}", e.toString());
            return BaseResponse.ofFail(ResponseCode.FAIL.getCode(), ResponseCode.FAIL.getMessage());
        }
    }

    public LocalDateTime stringToLocalDateTime(String stringTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(stringTime,formatter);
    }

    public String localDateTimeToString(LocalDateTime localDateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);
    }





}
