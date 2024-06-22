package com.example.chat.common.model;

import lombok.Data;

@Data
public class BaseRequest<T> {
    private String timestamp;
    private T requestBody;
    public BaseRequest() {
        this.timestamp = String.valueOf(System.currentTimeMillis());
    }
}
