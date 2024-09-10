package com.backend.bookstore.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.backend.bookstore.models.Order;

public interface OrderService {

    ResponseEntity<String> generateReport(Map<String, Object> requestMap);

    ResponseEntity<List<Order>> getOrder();

    ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap);

    ResponseEntity<String> deleteOrder(Integer id);
    
}
