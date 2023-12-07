package com.example.apiRESTBooks.service;

import com.example.apiRESTBooks.models.Category;
import com.example.apiRESTBooks.response.CategoryResponseREST;
import org.springframework.http.ResponseEntity;

public interface ICategoryService {
    ResponseEntity<CategoryResponseREST> searchCategories();
    ResponseEntity<CategoryResponseREST> searchCategoryById(Long id);
    ResponseEntity<CategoryResponseREST> newCategory(Category category);
    ResponseEntity<CategoryResponseREST> updateCategory(Category category,Long id);
    ResponseEntity<CategoryResponseREST> deleteCategory(Long id);
}
