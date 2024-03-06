package com.jwt.example.JwtExample.models;


import com.jwt.example.JwtExample.entities.User;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JwtResponse {
    private String jwtToken;
    private String username;
    private User user;
    private Collection<? extends GrantedAuthority> authorities;

    public JwtResponse(String jwtToken, String username, Collection<? extends GrantedAuthority> authorities) {
        this.jwtToken = jwtToken;
        this.username = username;
        this.authorities = authorities;
    }
    public JwtResponse(User user,String jwtToken){
        this.user=user;
        this.jwtToken=jwtToken;
    }

    // Getters and setters
    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

}


