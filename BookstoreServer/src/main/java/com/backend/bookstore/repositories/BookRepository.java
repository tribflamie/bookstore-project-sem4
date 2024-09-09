package com.backend.bookstore.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import com.backend.bookstore.dtos.BookDto;
import com.backend.bookstore.models.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

    List<BookDto> getAllBook();

    @Modifying
    @Transactional
    Integer updateBookStatus(@Param("status") String status, @Param("id") Integer id);

    List<BookDto> getBookByCategoryId(@Param("id") Integer id);

    List<BookDto> getBookById(@Param("id") Integer id);
    
}
