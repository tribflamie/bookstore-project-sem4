package com.backend.bookstore.services;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface DashboardService {

    ResponseEntity<Map<String, Object>> getDetails();
    
}
