package com.example.chat.user.model;

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
    private Collection<? extends GrantedAuthority> authorities;

    public JwtResponse(String accessToken, String userId, Collection<? extends GrantedAuthority> authorities){
        this.token = accessToken;
        this.userId = userId;
        this.authorities = authorities;

    }
}
