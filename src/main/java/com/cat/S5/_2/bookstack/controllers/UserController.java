package com.cat.S5._2.bookstack.controllers;

import com.cat.S5._2.bookstack.dtos.user.*;
import com.cat.S5._2.bookstack.dtos.userbook.UserBookDto;
import com.cat.S5._2.bookstack.enums.UserRole;
import com.cat.S5._2.bookstack.services.UserService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/getByRole/{roleName}")
        public  ResponseEntity<List<UserDto>> getByRole(@PathVariable String roleName){
        return ResponseEntity.ok(userService.findByRole(UserRole.valueOf(roleName)));
            //Si la valeur ne correspond à aucun enum, Spring lève une MethodArgumentTypeMismatchException
        // AUTOMATIQUEMENT SANS AVOIR RIEN A FAIRE
        }

        @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.findDtoById(id));
        }


        @PatchMapping("/{id}")
        @PreAuthorize("(authentication.principal.username == @userRepository.findById(#id).orElseThrow().username)  or hasRole('ADMIN')")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody @Valid UpdateUserDto updateUserDto){
        return ResponseEntity.ok(userService.updateUser(id, updateUserDto));
        }

        @PatchMapping("{id}/password")
        @PreAuthorize("(authentication.principal.username == @userRepository.findById(#id).orElseThrow().username)  or hasRole('ADMIN')") //ici principal.username fait ref á la methode getUsername{return this.email) dans User
                                                                                // pour utiliser @PreAuthorize("#user.id == principal.id") il faudrait creer une CustomUserDetails classe
    public ResponseEntity<String> updatePassword(@PathVariable Long id,@RequestBody @Valid PasswordDto newPass){
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
           System.out.println("AUTHENTICATION PRINCIPAL: " + auth.getPrincipal());
            userService.updatePassword(id, newPass);
        return ResponseEntity.ok("Your password has been updated");
        }

    @PreAuthorize("hasRole('ADMIN')")
      @DeleteMapping("{id}")
      public ResponseEntity<String> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("The user has been deleted");
      }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{userId}/toggle-role")
    public ResponseEntity<UserDto> toggleUserRole(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.toggleUserRole(userId));
    }




    }
