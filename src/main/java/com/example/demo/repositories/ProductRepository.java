package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Product;
import com.example.demo.entities.User;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByOwner(User owner);
}
