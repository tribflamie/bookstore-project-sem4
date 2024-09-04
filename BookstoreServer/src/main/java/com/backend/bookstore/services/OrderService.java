package com.backend.bookstore.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.backend.bookstore.dtos.OrderDto;

public interface OrderService {

    ResponseEntity<String> createOrder(Map<String, Object> requestMap);

    ResponseEntity<List<OrderDto>> getAllOrders();

    ResponseEntity<OrderDto> getOrderById(Integer orderId);

    ResponseEntity<String> updateOrder(Map<String, Object> requestMap);
}