package com.backend.bookstore.mappers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path="/order")
public interface OrderMapper {
    @PostMapping(path="/generateReport")
    ResponseEntity<String> generateReport(@RequestBody Map<String, Object> requestMap);


}
