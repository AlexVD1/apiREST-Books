package com.example.apiRESTBooks.controllers;

import com.example.apiRESTBooks.models.Book;
import com.example.apiRESTBooks.response.BookResponseREST;
import com.example.apiRESTBooks.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1")
public class BookRestController {
    @Autowired
    private IBookService service;

    @GetMapping("/book")
    public ResponseEntity<BookResponseREST> searchBooks(){
        return service.searchBooks();
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookResponseREST> searchBooks(@PathVariable Long id){
        return service.searchBookById(id);
    }

    @PostMapping("/book")
    public ResponseEntity<BookResponseREST> searchBooks(@RequestBody Book book){
        return service.newBook(book);
    }
    @PutMapping("/book/{id}")
    public ResponseEntity<BookResponseREST> searchBooks(@PathVariable Long id,@RequestBody Book book){
        return service.updateBook(book,id);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<BookResponseREST> deleteBook(@PathVariable Long id){
        return service.deleteBook(id);
    }

}
