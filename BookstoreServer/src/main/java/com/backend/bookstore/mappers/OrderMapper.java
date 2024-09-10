package com.backend.bookstore.mappers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backend.bookstore.models.Order;

@RequestMapping(path="/order")
public interface OrderMapper {
    @PostMapping(path="/generateReport")
    ResponseEntity<String> generateReport(@RequestBody Map<String, Object> requestMap);

    @GetMapping(path="/getOrder")
    ResponseEntity<List<Order>> getOrder();

    @PostMapping(path="/getPdf")
    ResponseEntity<byte[]> getPdf(@RequestBody Map<String, Object> requestMap);

    @PostMapping(path="/delete/{id}")
    ResponseEntity<String> deleteOrder(@PathVariable Integer id);
}
