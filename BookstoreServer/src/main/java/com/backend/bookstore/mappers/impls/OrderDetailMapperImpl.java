package com.backend.bookstore.mappers.impls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backend.bookstore.dtos.OrderDetailDto;
import com.backend.bookstore.mappers.OrderDetailMapper;
import com.backend.bookstore.services.OrderDetailService;
import com.backend.bookstore.utils.BookstoreUtil;

@RestController
public class OrderDetailMapperImpl implements OrderDetailMapper {

    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    public ResponseEntity<String> createOrderDetail(@RequestBody Map<String, Object> requestMap, @RequestParam Integer orderId) {
        try {
            return orderDetailService.createOrderDetail(requestMap, orderId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return BookstoreUtil.getResponseEntity("Something went wrong while creating the order detail.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<OrderDetailDto>> getAllOrderDetails() {
        try {
            return orderDetailService.getAllOrderDetails();
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<OrderDetailDto>> getOrderDetailsByOrderId(Integer orderId) {
        try {
            return orderDetailService.getOrderDetailsByOrderId(orderId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> updateOrderDetail(Map<String, Object> requestMap) {
        try {
            return orderDetailService.updateOrderDetail(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
            return BookstoreUtil.getResponseEntity("Something went wrong while updating the order detail.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
