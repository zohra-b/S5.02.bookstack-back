package com.cat.S5._2.bookstack.security;

import com.cat.S5._2.bookstack.services.UserService;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity //remplace la configuration par défaut de Spring Security
@EnableMethodSecurity       // indisp pour pouvoir utiliser @Preauthorize @PostAuthorize, @Secured

public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public JwtAuthFilter jwtAuthFilter(UserDetailsService userDetailsService, JwtService jwtService){
        return new JwtAuthFilter(jwtService, userDetailsService);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, JwtAuthFilter jwtAuthFilter) throws Exception{
        httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth         //L'ordre est important (du plus spécifique au plus général).
                        .requestMatchers(
                                "/api/test",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-resources/**",
                                "/webjars/**",
                                "/api-docs/**",  // Add this line
                                "/api-docs",    // And this line
                                "/configuration/ui",
                                "/configuration/security",
                                "/api/auth/**",
                                "/api/books")
//                        .requestMatchers("/api/users/**").hasRole("ADMIN")
//                        .anyRequest().authenticated()
                        .permitAll() // TEMPORARY - allow all
                        .anyRequest().permitAll()
                )
                .csrf(AbstractHttpConfigurer::disable) // à activer en prod !! sauf si Jwt token créé
                                                                        // desactive le Cross-Site Request Forgery de Spring qui oblige à creer un token
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);


        return httpSecurity.build();
    }

    /// //////// A RECONFIGURER QUND IL Y AURA LE FRONT END ////////////////////////////
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*"));  // Autorise toutes les origines (temporaire)
        config.setAllowedMethods(List.of("*"));  // Autorise toutes les méthodes (GET, POST, etc.)
        config.setAllowedHeaders(List.of("*"));  // Autorise tous les headers
        config.setAllowCredentials(false);       // Désactive les credentials (important avec "*")

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

}
