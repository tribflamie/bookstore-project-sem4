package com.backend.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.bookstore.dtos.BookDto;
import com.backend.bookstore.models.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

    List<BookDto> getAllBook();
    
}
