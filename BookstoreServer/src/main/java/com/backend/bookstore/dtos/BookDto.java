package com.backend.bookstore.dtos;

import lombok.Data;

@Data
public class BookDto {
    Integer id;
    String name;
    String description;
    Double price;
    String status;
    Integer categoryId;
    String categoryName;
    Integer authorId;
    String authorName;

    public BookDto(Integer id, String name, String description, Double price, String status, Integer categoryId,
            String categoryName, Integer authorId, String authorName) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.status = status;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public BookDto() {
        
    }

}
