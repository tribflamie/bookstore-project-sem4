package com.backend.bookstore.services.impls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.bookstore.dtos.OrderDto;
import com.backend.bookstore.models.Order;
import com.backend.bookstore.models.User;
import com.backend.bookstore.repositories.OrderRepository;
import com.backend.bookstore.security.JwtFilter;
import com.backend.bookstore.services.OrderService;
import com.backend.bookstore.utils.BookstoreUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> createOrder(Map<String, Object> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                Order order = getOrderFromMap(requestMap, false);
                orderRepository.save(order);
                return BookstoreUtil.getResponseEntity("Order Created Successfully", HttpStatus.OK);
            } else {
                return BookstoreUtil.getResponseEntity("Unauthorized Access", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return BookstoreUtil.getResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        try {
            List<OrderDto> orderDtos = orderRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
            return new ResponseEntity<>(orderDtos, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<OrderDto> getOrderById(Integer orderId) {
        try {
            Optional<Order> optional = orderRepository.findById(orderId);
            if (optional.isPresent()) {
                return new ResponseEntity<>(convertToDto(optional.get()), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> updateOrder(Map<String, Object> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                Order order = getOrderFromMap(requestMap, true);
                orderRepository.save(order);
                return BookstoreUtil.getResponseEntity("Order Updated Successfully", HttpStatus.OK);
            } else {
                return BookstoreUtil.getResponseEntity("Unauthorized Access", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return BookstoreUtil.getResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Order getOrderFromMap(Map<String, Object> requestMap, Boolean isUpdate) {
        Order order = new Order();
        if (isUpdate) {
            order.setId(Integer.parseInt((String) requestMap.get("id")));
        }
        order.setUser(new User());
        order.setOrderDate(new Date()); 
        

        return order;
    }

    private OrderDto convertToDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setId(order.getId());
        dto.setUserId(order.getUser() != null ? order.getUser().getId() : null);
        dto.setOrderDate(order.getOrderDate().toString());
        dto.setStatus(order.getStatus());
        dto.setTotalPrice(order.getTotalPrice());
       

        return dto;
    }
}
