package com.backend.bookstore.mappers.impls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.backend.bookstore.mappers.AuthorMapper;
import com.backend.bookstore.models.Author;
import com.backend.bookstore.services.AuthorService;
import com.backend.bookstore.utils.BookstoreUtil;

@RestController
public class AuthorMapperImpl implements AuthorMapper{

    @Autowired
    AuthorService authorService;

    @Override
    public ResponseEntity<String> addNewAuthor(Map<String, String> requestMap) {
        try {
            return authorService.addNewAuthor(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BookstoreUtil.getResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<Author>> getAllAuthor(String filterValue) {
        try {
            return authorService.getAllAuthor(filterValue);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateAuthor(Map<String, String> requestMap) {
        try {
            return authorService.updateAuthor(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BookstoreUtil.getResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
