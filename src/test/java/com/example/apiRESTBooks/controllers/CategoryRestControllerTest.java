package com.example.apiRESTBooks.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.apiRESTBooks.response.CategoryResponseREST;
import com.example.apiRESTBooks.service.ICategoryService;
import com.example.apiRESTBooks.models.Category;

public class CategoryRestControllerTest {

    @InjectMocks
    CategoryRestController category;

    @Mock
    private ICategoryService service;
    
    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createTest(){
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Category cat = new Category(Long.valueOf(1),"Old","Old Books");

        when(service.newCategory(any(Category.class))).thenReturn(new ResponseEntity<CategoryResponseREST>(HttpStatus.OK));

        ResponseEntity<CategoryResponseREST>  response =category.newCategory(cat);

        assertEquals(HttpStatus.OK,response.getStatusCode());
        }
}
