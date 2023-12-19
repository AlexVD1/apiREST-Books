package com.example.apiRESTBooks.controllers;

import com.example.apiRESTBooks.models.Book;
import com.example.apiRESTBooks.models.Category;
import com.example.apiRESTBooks.response.BookResponseREST;
import com.example.apiRESTBooks.service.IBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class BookRestControllerTest {

    @InjectMocks
    BookRestController Book;

    @Mock
    private IBookService service;
    
    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createTest(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Category cat = new Category(Long.valueOf(1),"Old","Old Books");

        Book book1 = new Book(Long.valueOf(1),"Old","Description","Author",cat);

        when(service.newBook(any(Book.class))).thenReturn(new ResponseEntity<BookResponseREST>(HttpStatus.OK));

        ResponseEntity<BookResponseREST>  response =service.newBook(book1);

        assertEquals(HttpStatus.OK,response.getStatusCode());
        }
}
