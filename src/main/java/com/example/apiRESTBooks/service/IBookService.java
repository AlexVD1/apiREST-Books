package com.example.apiRESTBooks.service;

import com.example.apiRESTBooks.models.Book;
import com.example.apiRESTBooks.response.BookResponseREST;
import org.springframework.http.ResponseEntity;

public interface IBookService {
    ResponseEntity<BookResponseREST> searchBooks();
    ResponseEntity<BookResponseREST> searchBookById(Long id);
    ResponseEntity<BookResponseREST> newBook(Book book);
    ResponseEntity<BookResponseREST> updateBook(Book book,Long id);
    ResponseEntity<BookResponseREST> deleteBook(Long id);
}
