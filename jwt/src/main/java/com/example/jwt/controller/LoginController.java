package com.example.jwt.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.constants.SecurityConstants;
import com.example.jwt.domain.AuthenticationRequest;
import com.example.jwt.prop.JwtProp;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private JwtProp jwtProp;

    //login
    // - username
    // - password
    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) { //토근 생성하는 코드

        String username = request.getUsername();
        String password = request.getPassword();

        // 넘어오는지 확인
        log.info("username : " + username);
        log.info("password : " + password);

        // 임의로 사용자 권한 부여해보기
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        roles.add("ROLE_ADMIN");

        byte[] signingKey = jwtProp.getSecretKey().getBytes(); // 시크릿 키를 시그니처에 사용할 때는 바이트로 사용한다.

        // 토큰 생성
        String jwt = Jwts.builder()
                        .signWith(Keys.hmacShaKeyFor(signingKey), io.jsonwebtoken.SignatureAlgorithm.HS512)  // 서명에 사용할 키와 알고리즘 설정
                        .setHeaderParam("type", SecurityConstants.Token_TYPE)  // 헤더 설정
                        .setExpiration(new Date(System.currentTimeMillis() + 864000000))  // 토큰 만료 시간 설정 (10일)
                        .claim("uid", username)                                   // 클레임 설정: 사용자 아이디
                        .claim("rol", roles)                                      // 클레임 설정: 역할 정보
                        .compact();  
       
        log.info("jwt : " + jwt);

        // 생성된 토큰을 클라이언트에게 반환
        return new ResponseEntity<String>(jwt, HttpStatus.OK);
    }
    
}
