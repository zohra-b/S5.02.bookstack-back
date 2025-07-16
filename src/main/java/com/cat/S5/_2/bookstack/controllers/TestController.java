package com.cat.S5._2.bookstack.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api")
@Tag(name = "Test Controller", description = "Test endpoints")
public class TestController {

    @Operation(summary = "Test endpoint")
    @GetMapping("/test")
    public String test() {
        return "Test endpoint works";
    }
}