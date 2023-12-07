package com.example.apiRESTBooks.response;

import lombok.Getter;
import lombok.Setter;

public class BookResponseREST extends ResponseREST{
    @Getter @Setter
    private BookResponse bookResponse =new BookResponse();
}
