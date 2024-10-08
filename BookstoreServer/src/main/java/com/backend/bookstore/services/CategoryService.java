package com.backend.bookstore.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.backend.bookstore.models.Category;

public interface CategoryService {

    ResponseEntity<String> addNewCategory(Map<String, String> requestMap);
    
    ResponseEntity<List<Category>> getAllCategory(String filterValue);

    ResponseEntity<String> updateCategory(Map<String, String> requestMap);
}
