package com.backend.bookstore.services;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface OrderService {

    ResponseEntity<String> generateReport(Map<String, Object> requestMap);
    
}
