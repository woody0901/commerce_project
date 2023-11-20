package com.example.commerce.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

        response.setContentType("application/json;charset=UTF-8");

        String responseBody = "{\"message\": \"" + accessDeniedException.getMessage() + "\", \"url\": \"" + request.getRequestURI() + "\"}";

        response.getWriter().write(responseBody);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .exceptionHandling()
                .accessDeniedHandler(new CustomAccessDeniedHandler());
        return http.build();
    }
}