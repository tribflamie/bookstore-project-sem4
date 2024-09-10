package com.backend.bookstore.mappers.impls;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.backend.bookstore.mappers.OrderMapper;
import com.backend.bookstore.models.Order;
import com.backend.bookstore.services.OrderService;
import com.backend.bookstore.utils.BookstoreUtil;

@RestController
public class OrderMapperImpl implements OrderMapper{
    @Autowired
    OrderService orderService;
    
    @Override
    public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {
        try {
            return orderService.generateReport(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BookstoreUtil.getResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Order>> getOrder() {
        try {
           return orderService.getOrder();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap) {
        try {
            return orderService.getPdf(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<String> deleteOrder(Integer id) {
        try {
            return orderService.deleteOrder(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BookstoreUtil.getResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
