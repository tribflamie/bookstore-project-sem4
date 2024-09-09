package com.backend.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.bookstore.models.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    
}
