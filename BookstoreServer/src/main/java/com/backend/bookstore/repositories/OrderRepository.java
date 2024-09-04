package com.backend.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backend.bookstore.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    
}