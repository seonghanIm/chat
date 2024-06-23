package com.example.chat.domain.user.service;

import com.example.chat.domain.chatroom.model.ChatRoom;
import com.example.chat.domain.chatroom.model.ChatRoomDto;
import com.example.chat.domain.chatroom.repository.ChatRoomRepository;
import com.example.chat.domain.common.model.BaseRequest;
import com.example.chat.domain.common.model.BaseResponse;
import com.example.chat.domain.user.model.JwtResponse;
import com.example.chat.domain.user.model.User;
import com.example.chat.domain.user.model.UserDto;
import com.example.chat.domain.user.repository.UserRepository;
import com.example.chat.utils.JwtUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Transactional
    public BaseResponse<UserDto> createUser(UserDto reqDto){
        if (userRepository.existsByUserId(reqDto.getUserId())) {
            return BaseResponse.ofFail(400,"이미 존재하는 아이디 입니다.");
        }
        User user = userRepository.save(reqDto.toEntity());
        return BaseResponse.ofSuccess(user.fromEntity());
    }

    @Transactional
    public BaseResponse<ChatRoomDto> outChatRoom(UserDto reqDto){
        ChatRoom chatRoom = chatRoomRepository.findByRoomId(reqDto.getChatRoomId());
        User user = userRepository.findByUserId(reqDto.getUserId())
                .orElseThrow(()->new UsernameNotFoundException("User not found with user Id" + reqDto.getUserId()));

        user.outChatRoom(chatRoom);
        return BaseResponse.ofSuccess(chatRoom.fromEntity());
    }

    @Transactional
    public BaseResponse login(BaseRequest<UserDto> req){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getRequestBody().getUserId(),req.getRequestBody().getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return BaseResponse.ofSuccess(new JwtResponse(jwt,userDetails.getUsername(),userDetails.getAuthorities()));}

    @Transactional
    public BaseResponse resgisterUser(BaseRequest<UserDto> req){
        UserDto reqDto = req.getRequestBody();
        if(userRepository.existsByUserId(reqDto.getUserId())){
            return BaseResponse.ofFail(400,"이미 존재하는 아이디입니다.");
        }
        User user = reqDto.toEntity();
        user.setPassword(passwordEncoder.encode(reqDto.getPassword()));
        User resUser = userRepository.save(user);
        return BaseResponse.ofSuccess(resUser.fromEntity());
    }
}
