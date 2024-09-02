package com.backend.bookstore.mappers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backend.bookstore.dtos.UserDto;

@RequestMapping(path="/user")
public interface UserMapper {
    
    @PostMapping(path="/signup")
    ResponseEntity<String> signUp(@RequestBody(required = true) Map<String, String> requestMap);

    @PostMapping(path="/login")
    ResponseEntity<String> login(@RequestBody(required = true) Map<String, String> requestMap);
    
    @GetMapping(path="/get")
    ResponseEntity<List<UserDto>> getAllUser();

    @PostMapping(path="/update")
    ResponseEntity<String> update(@RequestBody(required = true) Map<String, String> requestMap);

}
