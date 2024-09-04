package com.backend.bookstore.dtos;

import java.util.List;

import lombok.Data;

@Data
public class OrderDto {
    private Integer id;
    private Integer userId;
    private String orderDate;
    private String status;
    private Double totalPrice;
    private List<OrderDetailDto> orderDetails;

    public OrderDto(){

    }

    public OrderDto(Integer id, Integer userId, String orderDate, String status, Double totalPrice, List<OrderDetailDto> orderDetails) {
        this.id = id;
        this.userId = userId;
        this.orderDate = orderDate;
        this.status = status;
        this.totalPrice = totalPrice;
        this.orderDetails = orderDetails;
    }
}