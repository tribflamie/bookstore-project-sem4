package com.backend.bookstore.mappers.impls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.backend.bookstore.dtos.BookDto;
import com.backend.bookstore.mappers.BookMapper;
import com.backend.bookstore.services.BookService;
import com.backend.bookstore.utils.BookstoreUtil;

@RestController
public class BookMapperImpl implements BookMapper{

    @Autowired
    BookService bookService;

    @Override
    public ResponseEntity<String> addNewBook(Map<String, String> requestMap) {
        try {
            return bookService.addNewBook(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BookstoreUtil.getResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<BookDto>> getAllBook() {
        try {
            return bookService.getAllBook();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateBook(Map<String, String> requestMap) {
        try {
            return bookService.updateBook(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BookstoreUtil.getResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteBook(Integer id) {
        try {
            return bookService.deleteBook(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BookstoreUtil.getResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }


    
}
