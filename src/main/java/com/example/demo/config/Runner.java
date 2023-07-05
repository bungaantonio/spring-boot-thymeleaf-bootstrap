package com.example.demo.config;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import com.example.demo.enums.Role;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.repositories.UserRepository;

@Component
class Runner implements CommandLineRunner {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private ProductRepository productRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Override
        public void run(String... args) throws Exception {
                final var u1 = new User("Sakaneno Paulo", "saka", "saka@gmail.com", "951005263",
                                passwordEncoder.encode("pass"),
                                Role.BUYER);
                final var u2 = new User("Bunga Antonio", "bungadas", "bungadas@gmail.com", "927636231",
                                passwordEncoder.encode("pass"),
                                Role.SELLER);

                userRepository.saveAll(List.of(u1, u2));

                productRepository.saveAll(
                                List.of(
                                                new Product("Computador", new BigDecimal("100000"), 2, u1)));
        }
}
