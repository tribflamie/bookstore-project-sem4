package com.backend.bookstore.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.backend.bookstore.dtos.BookDto;

public interface BookService {

    ResponseEntity<String> addNewBook(Map<String, String> requestMap);

    ResponseEntity<List<BookDto>> getAllBook();

    ResponseEntity<String> updateBook(Map<String, String> requestMap);

    ResponseEntity<String> deleteBook(Integer id);

    ResponseEntity<String> updateStatus(Map<String, String> requestMap);

    ResponseEntity<List<BookDto>> getBookByCategoryId(Integer id);

    ResponseEntity<List<BookDto>> getBookById(Integer id);
    
}
