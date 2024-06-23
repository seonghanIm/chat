package com.example.chat.domain.chatroom.model;

import lombok.Getter;

@Getter
public enum ResponseCode {

    SUCCESS(200,"success"),
    FAIL(400,"fail"),
    CUSTOM_FAIL(400,"");

    ResponseCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;
}
