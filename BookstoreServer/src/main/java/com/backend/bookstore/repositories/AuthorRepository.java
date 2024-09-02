package com.backend.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.bookstore.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer>{
    List<Author> getAllAuthor();
}
