package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
class Config {
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(
                        custom -> custom
                                .requestMatchers("/", "index.html", "/resources/**", "/register", "/login",
                                        "/css/**", ".css",
                                        "js", ".js", "/img/**", ".jpeg")
                                .permitAll()
                                .anyRequest().authenticated())
                .formLogin(custom -> custom.successForwardUrl("/home"));
        return http.build();
    }
}
