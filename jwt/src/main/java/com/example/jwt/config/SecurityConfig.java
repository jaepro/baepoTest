package com.example.jwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    //Spring Security 5.5 이상
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //시큐리티를 지금 직접적으로 설정하지는 않고 시큐리티의 설정을 비활성화 해놓는 작업
        //폼 기반 로그인 비활성화 
        http.formLogin( (login) -> login.disable() );

        //HTTP 기본 인증 비활성화
        http.httpBasic( (basic) -> basic.disable() );

        //CSRF 공격 방어 기능 비활성화 
        http.csrf( (csrf) -> csrf.disable() );

        //세션 비활성화 - 지금까지 세션에 사용자의 정보를 들고 있었지만 이제는 그럴 필요가 없으므로 세션 인증을 사용하지 않음
        http.sessionManagement( (management) -> management.sessionCreationPolicy( SessionCreationPolicy.STATELESS ));

       // 엔드포인트별 권한 설정
       http.authorizeHttpRequests(authorize -> authorize
       .requestMatchers("/login").permitAll()  // /login 경로는 인증 없이 접근 허용
       .anyRequest().authenticated()            // 그 외의 요청은 인증 필요
   );

        
        return http.build();  //HttpSecurity객체에서 build메소드를 호출하면 설정된 정보들을 가지고 
                                //SecurityFilterChain을 반환해줌

    }
}
