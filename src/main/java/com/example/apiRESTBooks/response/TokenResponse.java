package com.example.apiRESTBooks.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class TokenResponse {

    @Setter @Getter
    private String jwtToken;

}
