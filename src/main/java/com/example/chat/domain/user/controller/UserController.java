package com.example.chat.domain.user.controller;

import com.example.chat.domain.chatroom.model.ChatRoomDto;
import com.example.chat.domain.chatroom.model.ResponseCode;
import com.example.chat.domain.common.model.BaseRequest;
import com.example.chat.domain.common.model.BaseResponse;
import com.example.chat.domain.user.model.UserDto;
import com.example.chat.domain.user.repository.UserRepository;
import com.example.chat.domain.user.service.UserService;
import com.example.chat.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    @PostMapping("/out/chat_room")
    public BaseResponse<ChatRoomDto> outChatRoom(@RequestBody BaseRequest<UserDto> req, BindingResult result){
        if(result.hasErrors()){
            return BaseResponse.ofFail(ResponseCode.FAIL.getCode(), result.toString());
        }
        return userService.outChatRoom(req.getRequestBody());
    }

    @PostMapping("/login")
    public BaseResponse login(@RequestBody BaseRequest<UserDto> req,BindingResult result){
        if(result.hasErrors()){
            BaseResponse.ofFail(400,result.toString());
        }
        return userService.login(req);
    }

    @PostMapping("/signup")
    public BaseResponse registerUser(@RequestBody BaseRequest<UserDto> req, BindingResult result){
        if(result.hasErrors()){
            BaseResponse.ofFail(400,result.toString());
        }
        return userService.resgisterUser(req);
    }

    @GetMapping("/users")
    public BaseResponse getAllUser(){
        return userService.getAllUser();
    }





}
