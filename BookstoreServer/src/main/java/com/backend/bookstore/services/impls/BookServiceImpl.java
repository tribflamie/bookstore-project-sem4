package com.backend.bookstore.services.impls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.backend.bookstore.dtos.BookDto;
import com.backend.bookstore.models.Author;
import com.backend.bookstore.models.Book;
import com.backend.bookstore.models.Category;
import com.backend.bookstore.repositories.BookRepository;
import com.backend.bookstore.security.JwtFilter;
import com.backend.bookstore.services.BookService;
import com.backend.bookstore.utils.BookstoreUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    JwtFilter jwtFilter;

    @Override
    public ResponseEntity<String> addNewBook(Map<String, String> requestMap) {
        try {
            if (jwtFilter.isAdmin()) {
                if (validateBookMap(requestMap, false)) {
                    bookRepository.save(getBookFromMap(requestMap, false));
                    return BookstoreUtil.getResponseEntity("Book Added Successfully", HttpStatus.OK);
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

    private boolean validateBookMap(Map<String, String> requestMap, boolean validateId) {
        if (requestMap.containsKey("name")) {
            if (requestMap.containsKey("id") && validateId) {
                return true;
            } else if (!validateId) {
                return true;
            }
        }
        return false;
    }

    private Book getBookFromMap(Map<String, String> requestMap, Boolean isAdd) {
        Category category = new Category();
        category.setId(Integer.parseInt(requestMap.get("categoryId")));
        Author author = new Author();
        author.setId(Integer.parseInt(requestMap.get("authorId")));

        Book book = new Book();
        if (isAdd) {
            book.setId(Integer.parseInt(requestMap.get("id")));
        } else {
            book.setStatus("true");
        }

        book.setCategory(category);
        book.setAuthor(author);
        book.setName(requestMap.get("name"));
        book.setPrice(Double.parseDouble(requestMap.get("price")));
        book.setDescription(requestMap.get("description"));
        return book;

    }

    @Override
    public ResponseEntity<List<BookDto>> getAllBook() {
        try {
            return new ResponseEntity<>(bookRepository.getAllBook(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateBook(Map<String, String> requestMap) {
        try {
            if(jwtFilter.isAdmin()){
                if(validateBookMap(requestMap, true)){
                    Optional<Book> optional = bookRepository.findById(Integer.parseInt(requestMap.get("id")));
                    if(!optional.isEmpty()){
                        Book book = getBookFromMap(requestMap, true);
                        book.setStatus(optional.get().getStatus());
                        bookRepository.save(book);
                        return BookstoreUtil.getResponseEntity("Book Updated Successfully", HttpStatus.OK);
                    } else {
                        return BookstoreUtil.getResponseEntity("Book Id Does Not exist", HttpStatus.OK);
                    }
                } else {
                    return BookstoreUtil.getResponseEntity("Invalid Data", HttpStatus.BAD_REQUEST);
                }
            } else {
                return BookstoreUtil.getResponseEntity("Unauthorized Access", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return BookstoreUtil.getResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @SuppressWarnings("rawtypes")
    @Override
    public ResponseEntity<String> deleteBook(Integer id) {
        try {
            if(jwtFilter.isAdmin()){
                Optional optional = bookRepository.findById(id);
                if(!optional.isEmpty()){
                    bookRepository.deleteById(id);
                    return BookstoreUtil.getResponseEntity("Book Deleted Successfully", HttpStatus.OK);
                } else {
                    return BookstoreUtil.getResponseEntity("Book Id Does Not exist", HttpStatus.OK);
                }
            } else {
                return BookstoreUtil.getResponseEntity("Unauthorized Access", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BookstoreUtil.getResponseEntity("Something Went Wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
