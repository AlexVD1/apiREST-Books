package com.example.apiRESTBooks.service;

import com.example.apiRESTBooks.dao.ICategoryDAO;
import com.example.apiRESTBooks.models.Category;
import com.example.apiRESTBooks.response.CategoryResponseREST;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Transactional
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private ICategoryDAO iCategoryDAO;

    @Override
    public ResponseEntity<CategoryResponseREST> searchCategories() {

        log.info("Starting method searchCategories");

        CategoryResponseREST response= new CategoryResponseREST();
        try {
            List<Category> categoryList = (List<Category>) iCategoryDAO.findAll();
            response.getCategoryResponse().setCategoryList(categoryList);
            response.setMetadata("Response OK","00","success");
        }catch (Exception e){
            response.setMetadata("Response ERROR","-1","failed");
            log.error("ERROR CONSULTING CATEGORY: "+ e.getMessage());
            e.getStackTrace();
            return new ResponseEntity<CategoryResponseREST>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseREST>(response, HttpStatus.OK); //devuelve 200
    }

    @Override
    public ResponseEntity<CategoryResponseREST> searchCategoryById(Long id) {
        log.info("Starting method searchcategoryById");
        CategoryResponseREST response = new CategoryResponseREST();
        List<Category> categoryList = new ArrayList<>();
        try {
            Optional<Category> category = iCategoryDAO.findById(id);

            if (category.isPresent()) {
                categoryList.add(category.get());
                response.getCategoryResponse().setCategoryList(categoryList);
                response.setMetadata("Response OK", "00", "success");
            } else {
                log.error("Error searching category by ID");
                response.setMetadata("Response ERROR", "-2", "Category Not Found");
                return new ResponseEntity<CategoryResponseREST>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error searching category by ID");
            response.setMetadata("Response ERROR", "00", "failed");
            return new ResponseEntity<CategoryResponseREST>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseREST>(response, HttpStatus.OK); //devuelve 200
    }

    @Override
    public ResponseEntity<CategoryResponseREST> newCategory(Category category) {
        log.info("Starting method newCategory");

        CategoryResponseREST response = new CategoryResponseREST();
        List<Category> categoryList = new ArrayList<>();

        try {
            Category categorySaved = iCategoryDAO.save(category);
            categoryList.add(categorySaved);
            if (categorySaved != null){
                response.getCategoryResponse().setCategoryList(categoryList);
                response.setMetadata("Response OK", "00", "Category Saved");
            }
            else {
                response.getCategoryResponse().setCategoryList(categoryList);
                response.setMetadata("Response ERROR", "03", "Category Not Saved");
                return new ResponseEntity<CategoryResponseREST>(response, HttpStatus.BAD_REQUEST);
            }

        }catch (Exception e){
            log.error("ERROR creating a new Category: "+e);
            response.setMetadata("Response ERROR", "00", "failed");
            return new ResponseEntity<CategoryResponseREST>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<CategoryResponseREST>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryResponseREST> updateCategory(Category category, Long id) {

        log.info("Starting method updateCategory");

        CategoryResponseREST response = new CategoryResponseREST();
        List<Category> categoryList = new ArrayList<>();

        try {
            Optional<Category> categoryToUpdate = iCategoryDAO.findById(id);
            if (categoryToUpdate.isPresent()){
                categoryToUpdate.get().setDescription(category.getDescription());
                categoryToUpdate.get().setName(category.getName());

                Category categoryUpdated = iCategoryDAO.save(categoryToUpdate.get());
                categoryList.add(categoryUpdated);
                if (categoryUpdated != null){
                    response.getCategoryResponse().setCategoryList(categoryList);
                    response.setMetadata("Response OK", "00", "Category Updated");
                }else {
                    response.getCategoryResponse().setCategoryList(categoryList);
                    response.setMetadata("Response OK", "-3", "Category Not Updated");
                    return new ResponseEntity<CategoryResponseREST>(response, HttpStatus.BAD_REQUEST);
                }
            }else {
                response.getCategoryResponse().setCategoryList(categoryList);
                response.setMetadata("Response ERROR", "03", "Category Not Found");
                return new ResponseEntity<CategoryResponseREST>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("ERROR creating a new Category: "+e);
            response.setMetadata("Response ERROR", "00", "failed");
            return new ResponseEntity<CategoryResponseREST>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseREST>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CategoryResponseREST> deleteCategory(Long id) {
        log.info("Starting method deleteCategory");

        CategoryResponseREST response = new CategoryResponseREST();

        try{
            iCategoryDAO.deleteById(id);
            response.setMetadata("Response OK", "00", "Category Deleted");
        }catch (Exception e){
            log.error("ERROR Deleting category");
            response.setMetadata("Response ERROR", "00", "failed");
            return new ResponseEntity<CategoryResponseREST>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<CategoryResponseREST>(response, HttpStatus.OK);
    }
}
