package com.backend.bookstore.dtos;

import lombok.Data;

@Data
public class OrderDetailDto {
    private Integer id;
    private Integer orderId;
    private Integer bookId;
    private String bookName;
    private Integer quantity;
    private Double price;
    private Double discount;

    public OrderDetailDto(){

        
    }

    public OrderDetailDto(Integer id, Integer orderId, Integer bookId, String bookName, Integer quantity, Double price, Double discount) {
        this.id = id;
        this.orderId = orderId;
        this.bookId = bookId;
        this.bookName = bookName;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }
}
