package com.backend.bookstore.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.backend.bookstore.dtos.OrderDetailDto;

public interface OrderDetailService {

    ResponseEntity<String> createOrderDetail(Map<String, Object> requestMap, Integer orderId);

    ResponseEntity<List<OrderDetailDto>> getAllOrderDetails();

    ResponseEntity<List<OrderDetailDto>> getOrderDetailsByOrderId(Integer orderId);

    ResponseEntity<String> updateOrderDetail(Map<String, Object> requestMap);
}
