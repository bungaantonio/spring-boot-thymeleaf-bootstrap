package com.example.demo.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.config.JpaUserDetails;
import com.example.demo.repositories.UserRepository;

@Service
class JpaUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    JpaUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository
                .findByUsername(username)
                .map(JpaUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

}
