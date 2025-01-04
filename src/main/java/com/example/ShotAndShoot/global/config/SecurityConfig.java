package com.example.ShotAndShoot.global.config;

import com.example.ShotAndShoot.domain.member.service.MemberService;
import com.example.ShotAndShoot.global.jwt.JwtAuthenticationFilter;
import com.example.ShotAndShoot.global.jwt.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final MemberService memberService;

    public SecurityConfig(TokenProvider tokenProvider, MemberService memberService) {
        this.tokenProvider = tokenProvider;
        this.memberService = memberService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf((auth) -> auth.disable());
        http
                .authorizeHttpRequests((auth) -> auth
                .requestMatchers(HttpMethod.POST,"/api/v1/question/").authenticated()
                .requestMatchers("/api/v1/waste/", "api/v1/member/", "api/v1/member/logout", "api/v1/member/unregister").authenticated() //마이페이지, 회원[정보수정, 탈퇴, 로그아웃], 게시글[작성,수정,삭제], 댓글[작성,삭제],
                .anyRequest().permitAll());
        http
                .addFilterBefore(new JwtAuthenticationFilter(tokenProvider, memberService), UsernamePasswordAuthenticationFilter.class);
        http
                .sessionManagement((session) -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
