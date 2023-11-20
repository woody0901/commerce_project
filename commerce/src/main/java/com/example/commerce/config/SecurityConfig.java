package com.example.commerce.config;

import com.example.commerce.jwt.CustomAccessDeniedHandler;
import com.example.commerce.service.SellerService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
public class SecurityConfig {

    private final SellerService sellerService;

    public SecurityConfig(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/seller-page/**").access(sellerPageAuthorizationManager())
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exceptions -> exceptions
                        .accessDeniedHandler(accessDeniedHandler())
                )
                .formLogin(withDefaults());

        return http.build();
    }

    @Bean
    public AccessDeniedHandler accessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    private AuthorizationManager<RequestAuthorizationContext> sellerPageAuthorizationManager() {
        return (authSupplier, context) -> {
            Authentication authentication = authSupplier.get();
            String username = authentication.getName();
            boolean isApproved = sellerService.isSellerApproved(username);
            return new AuthorizationDecision(isApproved);
        };
    }
}