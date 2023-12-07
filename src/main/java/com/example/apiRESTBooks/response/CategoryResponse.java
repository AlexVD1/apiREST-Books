package com.example.apiRESTBooks.response;

import com.example.apiRESTBooks.models.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class CategoryResponse {

    @Getter @Setter
    private List<Category> categoryList;
}
