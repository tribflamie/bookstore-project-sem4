package com.backend.bookstore.mappers;

import java.util.Map;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backend.bookstore.dtos.BookDto;

@RequestMapping(path="/book")
public interface BookMapper {
    @PostMapping(path="/add")
    ResponseEntity<String> addNewBook(@RequestBody Map<String, String> requestMap);

    @GetMapping(path="/get")
    ResponseEntity<List<BookDto>> getAllBook();

    @PostMapping(path="/update")
    ResponseEntity<String> updateBook(@RequestBody Map<String, String> requestMap);

    @PostMapping(path="/delete/{id}")
    ResponseEntity<String> deleteBook(@PathVariable Integer id);

    @PostMapping(path="/updateStatus")
    ResponseEntity<String> updateStatus(@RequestBody Map<String, String> requestMap);

    @GetMapping(path="/getBookByCategoryId/{id}")
    ResponseEntity<List<BookDto>> getBookByCategoryId(@PathVariable Integer id);

    @GetMapping(path="/getBookById/{id}")
    ResponseEntity<List<BookDto>> getBookById(@PathVariable Integer id);
}
