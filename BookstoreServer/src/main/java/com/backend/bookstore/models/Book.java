package com.backend.bookstore.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@NamedQuery(name="Book.getAllBook", query = "select new com.backend.bookstore.dtos.BookDto(p.id, p.name, p.author, p.description, p.price, p.status, p.category.id, p.category.name) from Book p")
@NamedQuery(name="Book.updateBookStatus", query = "update Book p set p.status=:status where p.id=:id")
@NamedQuery(name="Book.getBookByCategoryId", query = "select new com.backend.bookstore.dtos.BookDto(p.id,p.name) from Book p where p.category.id=:id and p.status='true'")
@NamedQuery(name="Book.getBookById", query="select new com.backend.bookstore.dtos.BookDto(p.id, p.name, p.author, p.description, p.price) from Book p where p.id=:id")

@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "book")
public class Book implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_fk", nullable = false)
    private Category category;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;
    
    @Column(name = "price")
    private Double price;

    @Column(name = "status")
    private String status;
}
