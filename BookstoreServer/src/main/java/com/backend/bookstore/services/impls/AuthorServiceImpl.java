package com.backend.bookstore.services.impls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.bookstore.models.Author;
import com.backend.bookstore.repositories.AuthorRepository;
import com.backend.bookstore.security.JwtFilter;
import com.backend.bookstore.services.AuthorService;
import com.backend.bookstore.utils.BookstoreUtil;
import com.google.common.base.Strings;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> addNewAuthor(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateAuthorMap(requestMap, false)) {
                    authorRepository.save(getAuthorFromMap(requestMap, false));
                    return BookstoreUtil.getResponseEntity("Author Added Successfully", HttpStatus.OK);
                }
            } else {
                return BookstoreUtil.getResponseEntity("Unauthorized Access", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BookstoreUtil.getResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateAuthorMap(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("name")) {
            if (requestMap.containsKey("id") && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }

    private Author getAuthorFromMap(Map<String, String> requestMap, Boolean isAdd) {
        Author author = new Author();
        if (isAdd) {
            author.setId(Integer.parseInt(requestMap.get("id")));
        }
        author.setName(requestMap.get("name"));

        return author;
    }

    @Override
    public ResponseEntity<List<Author>> getAllAuthor(String filterValue) {
        try {
            if (!Strings.isNullOrEmpty(filterValue) && filterValue.equalsIgnoreCase("true")) {
                log.info("Inside if");
                return new ResponseEntity<List<Author>>(authorRepository.getAllAuthor(), HttpStatus.OK);
            }
            return new ResponseEntity<>(authorRepository.findAll(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<List<Author>>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateAuthor(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateAuthorMap(requestMap, true)) {
                    Optional<Author> optional = authorRepository.findById(Integer.parseInt(requestMap.get("id")));
                    if (!optional.isEmpty()) {
                        authorRepository.save(getAuthorFromMap(requestMap, true));
                        return BookstoreUtil.getResponseEntity("Author Updated Successfully", HttpStatus.OK);
                    } else {
                        return BookstoreUtil.getResponseEntity("Author Id Does Not exist", HttpStatus.OK);
                    }
                }
                return BookstoreUtil.getResponseEntity("Invalid Data", HttpStatus.BAD_REQUEST);
            } else {
                return BookstoreUtil.getResponseEntity("Unauthorized Access", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BookstoreUtil.getResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
