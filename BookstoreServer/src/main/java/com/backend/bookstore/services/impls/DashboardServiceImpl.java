package com.backend.bookstore.services.impls;

import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.bookstore.repositories.BookRepository;
import com.backend.bookstore.repositories.CategoryRepository;
import com.backend.bookstore.repositories.OrderRepository;
import com.backend.bookstore.services.DashboardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DashboardServiceImpl implements DashboardService{
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BookRepository bookRepository;
    
    @Autowired
    OrderRepository orderRepository;

    @Override
    public ResponseEntity<Map<String, Object>> getDetails() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("category", orderRepository.count());
        map.put("book", bookRepository.count());
        map.put("order", orderRepository.count());

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
    
}
