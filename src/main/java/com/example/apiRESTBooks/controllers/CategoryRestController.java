package com.example.apiRESTBooks.controllers;

import com.example.apiRESTBooks.models.Category;
import com.example.apiRESTBooks.response.CategoryResponseREST;
import com.example.apiRESTBooks.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class CategoryRestController {

    @Autowired
    private ICategoryService service;

    @GetMapping("/category")
    public ResponseEntity<CategoryResponseREST> consultCategories(){
        return service.searchCategories();
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<CategoryResponseREST> consultCategory(@PathVariable Long id){
        return service.searchCategoryById(id);
    }

    @PostMapping("/category")
    public ResponseEntity<CategoryResponseREST> newCategory(@RequestBody Category category){
        return service.newCategory(category);
    }

    @PutMapping("/category/{id}")
    public ResponseEntity<CategoryResponseREST> updateCategory(@RequestBody Category category,@PathVariable Long id){
        return service.updateCategory(category,id);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<CategoryResponseREST> deleteCategory(@PathVariable Long id){
        return service.deleteCategory(id);
    }
}
