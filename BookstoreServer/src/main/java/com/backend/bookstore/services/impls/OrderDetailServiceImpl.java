package com.backend.bookstore.services.impls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.bookstore.dtos.OrderDetailDto;
import com.backend.bookstore.models.Book;
import com.backend.bookstore.models.Order;
import com.backend.bookstore.models.OrderDetail;
import com.backend.bookstore.repositories.BookRepository; // Add this import
import com.backend.bookstore.repositories.OrderRepository; // Add this import
import com.backend.bookstore.repositories.OrderDetailRepository;
import com.backend.bookstore.security.JwtFilter;
import com.backend.bookstore.services.OrderDetailService;
import com.backend.bookstore.utils.BookstoreUtil;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderRepository orderRepository; 

    @Autowired
    private BookRepository bookRepository; 

    @Autowired
    private JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> createOrderDetail(Map<String, Object> requestMap, Integer orderId) {
        try {
            if (jwtFilter.isAdmin()) {
                OrderDetail orderDetail = getOrderDetailFromMap(requestMap, orderId);
                orderDetailRepository.save(orderDetail);
                return BookstoreUtil.getResponseEntity("Order Detail Created Successfully", HttpStatus.OK);
            } else {
                return BookstoreUtil.getResponseEntity("Unauthorized Access", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return BookstoreUtil.getResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<OrderDetailDto>> getAllOrderDetails() {
        try {
            List<OrderDetailDto> orderDetailDtos = orderDetailRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
            return new ResponseEntity<>(orderDetailDtos, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<OrderDetailDto>> getOrderDetailsByOrderId(Integer orderId) {
        try {
            List<OrderDetailDto> orderDetailDtos = orderDetailRepository.findByOrderId(orderId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
            return new ResponseEntity<>(orderDetailDtos, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> updateOrderDetail(Map<String, Object> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                OrderDetail orderDetail = getOrderDetailFromMap(requestMap, null);
                orderDetailRepository.save(orderDetail);
                return BookstoreUtil.getResponseEntity("Order Detail Updated Successfully", HttpStatus.OK);
            } else {
                return BookstoreUtil.getResponseEntity("Unauthorized Access", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return BookstoreUtil.getResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private OrderDetail getOrderDetailFromMap(Map<String, Object> requestMap, Integer orderId) {
        OrderDetail orderDetail = new OrderDetail();
        if (orderId != null) {
            Optional<Order> orderOptional = orderRepository.findById(orderId);
            if (orderOptional.isPresent()) {
                orderDetail.setOrder(orderOptional.get());
            } else {
                throw new RuntimeException("Order not found");
            }
        }

        Integer bookId = (Integer) requestMap.get("bookId");
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isPresent()) {
            orderDetail.setBook(bookOptional.get());
        } else {
            throw new RuntimeException("Book not found");
        }

        orderDetail.setQuantity((Integer) requestMap.get("quantity"));
        orderDetail.setPrice((Double) requestMap.get("price"));
        orderDetail.setDiscount((Double) requestMap.get("discount"));

        return orderDetail;
    }

    private OrderDetailDto convertToDto(OrderDetail orderDetail) {
        OrderDetailDto dto = new OrderDetailDto();
        dto.setId(orderDetail.getId());
        dto.setOrderId(orderDetail.getOrder() != null ? orderDetail.getOrder().getId() : null);
        dto.setBookId(orderDetail.getBook() != null ? orderDetail.getBook().getId() : null);
        dto.setBookName(orderDetail.getBook() != null ? orderDetail.getBook().getName() : null); 
        dto.setQuantity(orderDetail.getQuantity());
        dto.setPrice(orderDetail.getPrice());
        dto.setDiscount(orderDetail.getDiscount());

        return dto;
    }
}
