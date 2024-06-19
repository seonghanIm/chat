package com.example.chat.common.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
public class BaseResponse<T> {

    public BaseResponse(int code, String message){
        this.code = code;
        this.message = message;
    }
    private int code;
    private String message;
    private T responseBody;

    public static <T> BaseResponse<T> ofSuccess(T data) {
        return new BaseResponse<>(200, "Success", data);
    }

    public static <T> BaseResponse<T> ofFail(int code, String message) {
        return new BaseResponse<>(code, message);
    }

    
}
