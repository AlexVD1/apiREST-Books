package com.example.apiRESTBooks.service;

import com.example.apiRESTBooks.dao.IBookDAO;
import com.example.apiRESTBooks.models.Book;
import com.example.apiRESTBooks.response.BookResponseREST;
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
public class BookServiceImpl implements IBookService{

    @Autowired
    private IBookDAO iBookDAO;

    @Override
    public ResponseEntity<BookResponseREST> searchBooks() {
        log.info("Starting Method searchBooks");
        BookResponseREST response = new BookResponseREST();
        try {
            List<Book> books = (List<Book>) iBookDAO.findAll();
            response.getBookResponse().setBookList(books);
            if (books.isEmpty()){
                response.setMetadata("Response OK","-1","No books to list");
                return new ResponseEntity<BookResponseREST>(response, HttpStatus.NOT_FOUND);
            }
            else {
                response.getBookResponse().setBookList(books);
                response.setMetadata("Response OK", "01", "success");
            }
        } catch (Exception e){
            log.error("Error Listing Books "+e);
            response.setMetadata("Response ERROR","-1","failed");
            return new ResponseEntity<BookResponseREST>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<BookResponseREST>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookResponseREST> searchBookById(Long id) {
        log.info("Starting Method searchBookById");

        BookResponseREST response = new BookResponseREST();
        List<Book> bookList = new ArrayList<>();
        try {
            Optional<Book> bookFound = iBookDAO.findById(id);
            if (bookFound.isPresent()){
                bookList.add(bookFound.get());
                response.getBookResponse().setBookList(bookList);
                response.setMetadata("Response OK","01","success");
            }else {
                log.error("Error searching book by ID");
                response.setMetadata("Response ERROR","-1","Book Not Found");
                return new ResponseEntity<BookResponseREST>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            log.error("Error Listing Books "+e);
            response.setMetadata("Response ERROR","-1","failed");
            return new ResponseEntity<BookResponseREST>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<BookResponseREST>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookResponseREST> newBook(Book book) {
        log.info("Starting Method newBookById");

        BookResponseREST response = new BookResponseREST();
        List<Book> bookList = new ArrayList<>();

        try {
            Book savedBook = iBookDAO.save(book);
            if (savedBook!=null){
                bookList.add(savedBook);
                response.getBookResponse().setBookList(bookList);
                response.setMetadata("Response OK","01","Book Saved");
            }else {
                response.setMetadata("Response ERROR","-1","Book Not Saved");
                return new ResponseEntity<BookResponseREST>(response, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error Creating Book "+e);
            response.setMetadata("Response ERROR","-1","failed");
            return new ResponseEntity<BookResponseREST>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<BookResponseREST>(response,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookResponseREST> updateBook(Book book, Long id) {
        log.info("Starting Method updateBook");

        BookResponseREST response = new BookResponseREST();
        List<Book> bookList = new ArrayList<>();

        try {
            Optional<Book> bookToUpdate = iBookDAO.findById(id);
            if(bookToUpdate.isPresent()){
                bookToUpdate.get().setDescription(book.getDescription());
                bookToUpdate.get().setCategory(book.getCategory());
                bookToUpdate.get().setAuthor(book.getAuthor());
                bookToUpdate.get().setTitle(book.getTitle());
                Book bookUpdated = iBookDAO.save(bookToUpdate.get());
                if (bookUpdated!=null){
                    bookList.add(bookToUpdate.get());
                    response.getBookResponse().setBookList(bookList);
                    response.setMetadata("Response OK","01","Book Updated");
                }
                else {
                    response.setMetadata("Response ERROR", "-1", "Book Not Saved");
                    return new ResponseEntity<BookResponseREST>(response, HttpStatus.BAD_REQUEST);
                }
            }else {
                response.setMetadata("Response ERROR","-1","Book Not Found to Update");
                return new ResponseEntity<BookResponseREST>(response, HttpStatus.NOT_FOUND);
            }

        }catch (Exception e){
            log.error("Error Updating Book "+e);
            response.setMetadata("Response ERROR","-1","failed");
            return new ResponseEntity<BookResponseREST>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<BookResponseREST>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BookResponseREST> deleteBook(Long id) {
        log.info("Starting Method deleteBook");
        BookResponseREST response = new BookResponseREST();

        try {
            iBookDAO.deleteById(id);
            response.setMetadata("Response OK", "01", "Book Deleted");
        }
        catch (Exception e){
            log.error("Error Deleting Book "+e);
            response.setMetadata("Response ERROR","-1","failed");
            return new ResponseEntity<BookResponseREST>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<BookResponseREST>(response, HttpStatus.OK);
    }
}
