package com.example.dio.repository;

import com.example.dio.model.CartItem;
import com.example.dio.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
