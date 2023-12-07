package com.example.apiRESTBooks.request;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class AuthRequest implements Serializable{
    private static final long serialVersionUID=1L;

    @Getter @Setter
    private String user;

    @Getter @Setter
    private String password;

}
