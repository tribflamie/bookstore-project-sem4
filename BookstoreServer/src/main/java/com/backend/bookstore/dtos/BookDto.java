package com.backend.bookstore.dtos;

import lombok.Data;

@Data
public class BookDto {
    Integer id;
    String name;
    String author;
    String description;
    Double price;
    String status;
    Integer categoryId;
    String categoryName;

    public BookDto() {

    }

    public BookDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public BookDto(Integer id, String name, String author, String description, Double price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.description = description;
        this.price = price;
    }

    public BookDto(Integer id, String name, String author, String description, Double price, String status,
            Integer categoryId, String categoryName) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.description = description;
        this.price = price;
        this.status = status;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

}
