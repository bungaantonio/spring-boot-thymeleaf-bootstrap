package com.example.demo.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.enums.Role;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;

@Controller
class HomeController {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    HomeController(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @RequestMapping("/home")
    String home(@AuthenticationPrincipal UserDetails principal, Model model) {
        this.userRepository
                .findByUsername(principal.getUsername())
                .ifPresent(user -> {
                    model.addAttribute("name", user.getName());
                    model.addAttribute("role", user.getRole().toString());

                    if (user.getRole() == Role.SELLER) {
                        model.addAttribute("products", this.productRepository.findByOwner(user));
                    }
                });

        return "home";
    }
}
