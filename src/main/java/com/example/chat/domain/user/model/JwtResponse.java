package com.example.chat.domain.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String userId;
    private String userName;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtResponse(String accessToken, String userId, String userName, Collection<? extends GrantedAuthority> authorities){
        this.token = accessToken;
        this.userId = userId;
        this.userName = userName;
        this.authorities = authorities;


    }
}
