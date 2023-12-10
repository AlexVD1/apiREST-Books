package com.example.apiRESTBooks.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.apiRESTBooks.models.Category;
import com.example.apiRESTBooks.dao.ICategoryDAO;
import com.example.apiRESTBooks.response.CategoryResponseREST;

public class CategoryServiceImplTest {

    @InjectMocks //SE INYECTAN TODOS LOS MOCKS EN LA CLASE PARA UTILIZARLA
    CategoryServiceImpl service;

    @Mock //SE PUEDEN TENER X CANTIDAD DE MOCKS COMO DEPENDENCIAS TENGA EL IMPL
    ICategoryDAO iCategoryDAO;

    List<Category> list = new ArrayList<Category>();

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        this.loadCategories();
    }
    
    @Test
    public void searchCategoriesTest(){
        when(iCategoryDAO.findAll()).thenReturn(list);

        ResponseEntity<CategoryResponseREST> response =service.searchCategories();

        assertEquals(4, response.getBody().getCategoryResponse().getCategoryList().size());

        verify(iCategoryDAO,times(1)).findAll();
    }

    @Test
    public void searchCategoryByIdTest(){
        Optional<Category> opt= Optional.of(list.get(1)); 
        
        when(iCategoryDAO.findById(1L)).thenReturn(opt);

        ResponseEntity<CategoryResponseREST> response =service.searchCategoryById(1L);

        assertEquals("Drama", response.getBody().getCategoryResponse().getCategoryList().get(0).getName());

        verify(iCategoryDAO,times(1)).findById(1L);
    }

    public void loadCategories(){
        Category cat1 = new Category(Long.valueOf(1),"Thriller","Thriller Books");
        Category cat2 = new Category(Long.valueOf(2),"Drama","Drama Books");
        Category cat3 = new Category(Long.valueOf(3),"Romance","Romance Books");
        Category cat4 = new Category(Long.valueOf(4),"Action","Action Books");

        list.add(cat1);
        list.add(cat2);
        list.add(cat3);
        list.add(cat4);
    }

}
