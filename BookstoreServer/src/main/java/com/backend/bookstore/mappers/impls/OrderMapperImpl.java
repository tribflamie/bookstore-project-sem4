package com.backend.bookstore.mappers.impls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.backend.bookstore.dtos.OrderDto;
import com.backend.bookstore.mappers.OrderMapper;
import com.backend.bookstore.services.OrderService;
import com.backend.bookstore.utils.BookstoreUtil;

@RestController
public class OrderMapperImpl implements OrderMapper {

    @Autowired
    private OrderService orderService;

    @Override
    public ResponseEntity<String> createOrder(Map<String, Object> requestMap) {
        try {
            return orderService.createOrder(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            return BookstoreUtil.getResponseEntity("Something went wrong while creating the order.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        try {
            List<OrderDto> orders = orderService.getAllOrders().getBody(); // Ensure getAllOrders returns ResponseEntity<List<OrderDto>>
            if (orders == null) {
                orders = new ArrayList<>();
            }
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<OrderDto> getOrderById(Integer orderId) {
        try {
            OrderDto order = orderService.getOrderById(orderId).getBody(); // Ensure getOrderById returns ResponseEntity<OrderDto>
            if (order == null) {
               order = new OrderDto();
            }
            return new ResponseEntity<>(order, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new OrderDto(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> updateOrder(Map<String, Object> requestMap) {
        try {
            return orderService.updateOrder(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            return BookstoreUtil.getResponseEntity("Something went wrong while updating the order.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}