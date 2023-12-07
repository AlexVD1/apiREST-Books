package com.example.apiRESTBooks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfigSecurity {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails alexbeat=User.builder()
        .username("alexbeat")
        .password("{noop}@Spring#jfc#RaS2024")
        .roles("Gamma")
        .build();

        UserDetails user01=User.builder()
                .username("user01")
                .password("{noop}@Spring#jfc#RaS2024")
                .roles("Gamma")
                .build();

        UserDetails user02=User.builder()
                .username("user02")
                .password("{noop}@Spring#jfc#RaS2024")
                .roles("Gamma","Admin")
                .build();
        return new InMemoryUserDetailsManager(alexbeat,user02,user01);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configure -> {
           configure
                   .requestMatchers(HttpMethod.GET,"/v1/book").hasRole("Gamma")
                   .requestMatchers(HttpMethod.GET,"/v1/book/**").hasRole("Gamma")
                   .requestMatchers(HttpMethod.POST,"/v1/book").hasRole("Admin")
                   .requestMatchers(HttpMethod.PUT,"/v1/book/**").hasRole("Admin")
                   .requestMatchers(HttpMethod.DELETE,"/v1/book/**").hasRole("Admin")
                   .requestMatchers(HttpMethod.GET,"/v1/category").hasRole("Gamma")
                   .requestMatchers(HttpMethod.GET,"/v1/category/**").hasRole("Gamma")
                   .requestMatchers(HttpMethod.POST,"/v1/category").hasRole("Admin")
                   .requestMatchers(HttpMethod.PUT,"/v1/category/**").hasRole("Admin")
                   .requestMatchers(HttpMethod.DELETE,"/v1/category/**").hasRole("Admin");
        });

        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        
        return http.build();
    }
}
