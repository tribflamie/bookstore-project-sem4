package com.backend.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.backend.bookstore.models.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> getAllOrder();

    List<Order> getOrderByUserName(@Param("username") String username);
    
}
