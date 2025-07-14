package com.cat.S5._2.bookstack.controllers;

import com.cat.S5._2.bookstack.dtos.user.UserDto;
import com.cat.S5._2.bookstack.dtos.user.UserSummaryDto;
import com.cat.S5._2.bookstack.entities.Role;
import com.cat.S5._2.bookstack.services.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public List<UserDto> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/getByRole/{role}")
        public List<UserDto> getByRole(@PathVariable Role role){
            return userService.findByRole(role);
        }
    }
