package com.example.ShotAndShoot.global.jwt;

import com.example.ShotAndShoot.domain.member.service.MemberService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final MemberService memberService;

    public JwtAuthenticationFilter(TokenProvider tokenProvider, MemberService memberService) {
        this.tokenProvider = tokenProvider;
        this.memberService = memberService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = getTokenFromRequest(request);

        if (token != null) {
            if (tokenProvider.validateToken(token)) {
                String userId = tokenProvider.getUserIdFromToken(token);
                setAuthentication(userId);
            } else  {
                String userId = tokenProvider.getUserIdFromToken(token);
                if (userId != null && memberService.isValidRefreshToken(userId)) {
                    // RefreshToken이 유효한 경우 새로운 AccessToken 발급
                    String newAccessToken = tokenProvider.createAccessToken(userId);
                    response.setHeader("Authorization", "Bearer " + newAccessToken);
                    setAuthentication(userId);
                } else {
                    // RefreshToken이 없거나 유효하지 않음
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인이 필요합니다.");
                    return;
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private void setAuthentication(String userId) {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userId, null, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}

