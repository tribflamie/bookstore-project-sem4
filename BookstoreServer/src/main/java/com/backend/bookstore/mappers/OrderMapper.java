package com.backend.bookstore.mappers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.backend.bookstore.dtos.OrderDto;

@RequestMapping(path = "/order")
public interface OrderMapper {

    @PostMapping(path = "/create")
    ResponseEntity<String> createOrder(@RequestBody Map<String, Object> requestMap);

    @GetMapping(path = "/getAll")
    ResponseEntity<List<OrderDto>> getAllOrders();

    @GetMapping(path = "/getById")
    ResponseEntity<OrderDto> getOrderById(@RequestParam Integer orderId);

    @PostMapping(path = "/update")
    ResponseEntity<String> updateOrder(@RequestBody Map<String, Object> requestMap);
}
