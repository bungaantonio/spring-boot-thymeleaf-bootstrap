package com.example.demo.config;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.example.demo.entities.User;

public class JpaUserDetails extends org.springframework.security.core.userdetails.User {
    private final User user;

    public JpaUserDetails(User user) {
        super(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole())));
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
