package com.example.chat.domain.chatmessage.model;

public enum MessageType {
    ENTER(" 님이 입장하셨습니다."),
    TALK(""),
    EXIT(" 님이 나가셨습니다.");

    MessageType(String defaultMessage){
        this.defaultMessage = defaultMessage;
    }

    private String defaultMessage;

    public String getDefaultMessage() {
        return defaultMessage;
    }
}
