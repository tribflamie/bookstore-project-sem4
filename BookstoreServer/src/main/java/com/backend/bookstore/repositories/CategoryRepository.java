package com.backend.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.bookstore.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
    List<Category> getAllCategory();
}
