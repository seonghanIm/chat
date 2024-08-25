package com.example.chat.domain.chatmessage.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class ChatMessageReqDto {
    private String roomId;
    private String startDate;
    private String endDate;
    private String messageType;
}

