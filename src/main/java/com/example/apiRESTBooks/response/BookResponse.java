package com.example.apiRESTBooks.response;

import com.example.apiRESTBooks.models.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class BookResponse {
    @Getter @Setter
    private List<Book> bookList;
}
