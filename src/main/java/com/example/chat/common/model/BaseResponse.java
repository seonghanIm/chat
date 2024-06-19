package com.example.chat.common.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@Setter
@ToString
@NoArgsConstructor
@SuperBuilder
public class BaseResponse {
    private int code;
    private String message;
    
}
