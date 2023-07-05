package com.example.demo.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.User;
import com.example.demo.enums.Role;
import com.example.demo.repositories.UserRepository;

@Controller
public class RegisterController {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    RegisterController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    String register(
            @RequestParam String name,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam String phone,
            @RequestParam String password,
            @RequestParam Role role) {
        this.userRepository.save(
                new User(
                        name,
                        username,
                        email,
                        phone,
                        this.passwordEncoder.encode(password),
                        role));

        return "login";
    }
}
