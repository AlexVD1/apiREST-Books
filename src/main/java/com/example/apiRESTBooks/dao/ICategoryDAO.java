package com.example.apiRESTBooks.dao;

import com.example.apiRESTBooks.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryDAO extends CrudRepository <Category,Long> {

}
