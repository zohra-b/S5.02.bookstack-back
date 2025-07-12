package com.cat.S5._2.bookstack.config;

import com.cat.S5._2.bookstack.security.JwtAuthFilter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity //remplace la configuration par défaut de Spring Security
public class SecurityConfig {
  private final JwtAuthFilter jwtAuthFilter;
    public SecurityConfig(JwtAuthFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }


    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeHttpRequests(auth -> auth         //L'ordre est important (du plus spécifique au plus général).
                                .requestMatchers("/api/books").permitAll()
                                .requestMatchers("api/users/**").hasRole("ADMIN")
                                .anyRequest().authenticated() //Toutes les autres routes nécessitent une authentification
                )
                .csrf(csrf -> csrf.disable()) // à activer en prod !! sauf si Jwt token créé
                                                                        // desactive le Cross-Site Request Forgery de Spring qui oblige à creer un token
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        return httpSecurity.build();
    }

}
