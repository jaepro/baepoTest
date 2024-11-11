package com.example.jwt.domain;

import lombok.Data;

//이 파일은 DTO같은 역할임
@Data
public class AuthenticationRequest {
    private String username;
    private String password;
    
}
