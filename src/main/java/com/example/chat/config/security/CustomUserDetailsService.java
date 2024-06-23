package com.example.chat.config.security;

import com.example.chat.domain.user.model.User;
import com.example.chat.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;


@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId).orElseThrow(()->new UsernameNotFoundException("User not found with user Id" + userId));
        return new org.springframework.security.core.userdetails.User(user.getUserId(),user.getPassword(),new ArrayList<>());
    }
}
