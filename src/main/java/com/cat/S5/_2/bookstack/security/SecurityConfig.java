package com.cat.S5._2.bookstack.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity       // indisp pour pouvoir utiliser @Preauthorize
public class SecurityConfig {
}
