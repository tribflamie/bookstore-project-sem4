package com.backend.bookstore.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.backend.bookstore.models.Author;

public interface AuthorService {

    ResponseEntity<String> addNewAuthor(Map<String, String> requestMap);
    
    ResponseEntity<List<Author>> getAllAuthor(String filterValue);

    ResponseEntity<String> updateAuthor(Map<String, String> requestMap);
}
