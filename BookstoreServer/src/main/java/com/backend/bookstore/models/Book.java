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

@NamedQuery(name="Book.getAllBook", query = "select new com.backend.bookstore.dtos.BookDto(p.id,p.name,p.description,p.price,p.status,p.category.id,p.category.name,p.author.id,p.author.name) from Book p")


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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_fk", nullable = false)
    private Author author;

    @Column(name = "description")
    private String description;
    
    @Column(name = "price")
    private Double price;

    @Column(name = "status")
    private String status;
}
