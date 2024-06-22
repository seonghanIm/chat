package com.example.chat.user.controller;

import com.example.chat.chatroom.model.ChatRoomDto;
import com.example.chat.chatroom.model.ResponseCode;
import com.example.chat.common.model.BaseRequest;
import com.example.chat.common.model.BaseResponse;
import com.example.chat.user.model.JwtResponse;
import com.example.chat.user.model.User;
import com.example.chat.user.model.UserDto;
import com.example.chat.user.repository.UserRepository;
import com.example.chat.user.service.UserService;
import com.example.chat.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;

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
    @PostMapping("/create")
    public BaseResponse<UserDto> createUser(@RequestBody BaseRequest<UserDto> req, BindingResult result){
        if(result.hasErrors()){
            return BaseResponse.ofFail(400, result.toString());
        }
        return userService.createUser(req.getRequestBody());
    }

    @PostMapping("/out/chat_room")
    public BaseResponse<ChatRoomDto> outChatRoom(@RequestBody BaseRequest<UserDto> req, BindingResult result){
        if(result.hasErrors()){
            return BaseResponse.ofFail(ResponseCode.FAIL.getCode(), result.toString());
        }
        return userService.outChatRoom(req.getRequestBody());
    }

    @PostMapping("/login")
    public BaseResponse login(@RequestBody BaseRequest<UserDto> req){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getRequestBody().getUserId(),req.getRequestBody().getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return BaseResponse.ofSuccess(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/signup")
    public BaseResponse registerUser(@RequestBody BaseRequest<UserDto> req){
        UserDto reqDto = req.getRequestBody();
        if (userRepository.existsByUserId(reqDto.getUserId())) {
            return BaseResponse.ofFail(400,"이미 존재하는 아이디 입니다.");
        }
        User user = reqDto.toEntity();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User resUser = userRepository.save(user);
        return BaseResponse.ofSuccess(resUser.fromEntity());
    }





}
