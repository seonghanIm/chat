package com.example.chat.domain.common.controller;

import com.example.chat.domain.common.model.BaseRequest;
import com.example.chat.domain.user.model.UserDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @PostMapping
    public String hello(@RequestBody BaseRequest<UserDto> req) {
        return req.getUserId();
    }

}
