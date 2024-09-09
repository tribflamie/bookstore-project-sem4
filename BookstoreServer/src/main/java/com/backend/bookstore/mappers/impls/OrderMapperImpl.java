package com.backend.bookstore.mappers.impls;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.backend.bookstore.mappers.OrderMapper;
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
    
}
