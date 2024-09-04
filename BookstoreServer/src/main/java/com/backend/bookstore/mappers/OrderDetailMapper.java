package com.backend.bookstore.mappers;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.backend.bookstore.dtos.OrderDetailDto;

@RequestMapping(path = "/orderdetail")
public interface OrderDetailMapper {

    @PostMapping(path = "/create")
    ResponseEntity<String> createOrderDetail(@RequestBody Map<String, Object> requestMap, @RequestParam Integer orderId);

    @GetMapping(path = "/getAll")
    ResponseEntity<List<OrderDetailDto>> getAllOrderDetails();

    @GetMapping(path = "/getByOrderId")
    ResponseEntity<List<OrderDetailDto>> getOrderDetailsByOrderId(@RequestParam Integer orderId);

    @PostMapping(path = "/update")
    ResponseEntity<String> updateOrderDetail(@RequestBody Map<String, Object> requestMap);
}
