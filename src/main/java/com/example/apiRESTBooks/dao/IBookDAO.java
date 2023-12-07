package com.example.apiRESTBooks.dao;

import com.example.apiRESTBooks.models.Book;
import org.springframework.data.repository.CrudRepository;

public interface IBookDAO extends CrudRepository<Book,Long> {
}
