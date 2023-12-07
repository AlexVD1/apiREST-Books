package com.example.apiRESTBooks.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.apiRESTBooks.Utils.JwtReqFilter;

@Configuration
public class ConfigSecurity {

    @Autowired
    @Lazy
    private JwtReqFilter jwtReqFilter;

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configure -> {
           configure
                    //STARTS CONFIGURATION TO BOOKS
                   .requestMatchers(HttpMethod.GET,"/v1/book").hasRole("Gamma")
                   .requestMatchers(HttpMethod.GET,"/v1/book/**").hasRole("Gamma")
                   .requestMatchers(HttpMethod.POST,"/v1/book").hasRole("Admin")
                   .requestMatchers(HttpMethod.PUT,"/v1/book/**").hasRole("Admin")
                   .requestMatchers(HttpMethod.DELETE,"/v1/book/**").hasRole("Admin")
                   //STARTS CONFIGURATION TO CATEGORIES
                   .requestMatchers(HttpMethod.GET,"/v1/category").hasRole("Gamma")
                   .requestMatchers(HttpMethod.GET,"/v1/category/**").hasRole("Gamma")
                   .requestMatchers(HttpMethod.POST,"/v1/category").hasRole("Admin")
                   .requestMatchers(HttpMethod.PUT,"/v1/category/**").hasRole("Admin")
                   .requestMatchers(HttpMethod.DELETE,"/v1/category/**").hasRole("Admin")
                   //STARTS TOKEN CONFIGURATION
                   .requestMatchers(HttpMethod.POST,"/v1/authenticate").permitAll()
                   //START SPRINGDOC-Swagger CONFIG
                   .requestMatchers("/v3/api-docs/**","/swagger-ui/**","/swagger-ui.html").permitAll();
                   

        })
        .addFilterBefore(jwtReqFilter, UsernamePasswordAuthenticationFilter.class)
        .sessionManagement((session) -> session
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        
        
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        
        return http.build();
    }

    @Bean 
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
    throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
}
