package com.example.demo.controllers;

import java.math.BigDecimal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.config.JpaUserDetails;
import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;

@Controller
public class ProductController {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    ProductController(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @PostMapping("/register-product")
    @PreAuthorize("hasRole('SELLER')")
    String create(String name, BigDecimal price, int quantity, @AuthenticationPrincipal JpaUserDetails details) {
        this.productRepository.save(
                new Product(name, price, quantity, this.userRepository.getReferenceById(details.getUser().getId())));
        return "redirect:/home";
    }

    @DeleteMapping("/register-product")
    @PreAuthorize("hasRole('SELLER')")
    String delete(long id, @AuthenticationPrincipal JpaUserDetails details) {
        this.productRepository
                .findById(id)
                .filter(product -> product.getOwner().getId() == details.getUser().getId())
                .ifPresent(this.productRepository::delete);

        return "redirect:/home";
    }
}
