package com.example.apiRESTBooks.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.apiRESTBooks.Utils.JwtService;
import com.example.apiRESTBooks.request.AuthRequest;
import com.example.apiRESTBooks.response.TokenResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/v1")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;
    
    @PostMapping("/authenticate")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody AuthRequest request) {
        //Authenticate the User with Password
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUser(), request.getPassword()));
        
        //Loads the user by the username
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUser());

        //Generate the token with the User Details Loaded
        final String token = jwtService.generateToken(userDetails);

        //Returns Ok with the token generated
        return ResponseEntity.ok(new TokenResponse(token));
    }
    

}
