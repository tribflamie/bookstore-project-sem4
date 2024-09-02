package com.backend.bookstore.mappers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.backend.bookstore.models.Author;

@RequestMapping(path="/author")
public interface AuthorMapper {
    
    @PostMapping(path="/add")
    ResponseEntity<String> addNewAuthor(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping(path="/get")
    ResponseEntity<List<Author>> getAllAuthor(@RequestParam(required = false) String filterValue);

    @PostMapping(path="/update")
    ResponseEntity<String> updateAuthor(@RequestBody(required = true) Map<String, String> requestMap);

}