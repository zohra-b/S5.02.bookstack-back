package com.cat.S5._2.bookstack.controllers;

import com.cat.S5._2.bookstack.dtos.user.*;
import com.cat.S5._2.bookstack.dtos.userbook.UserBookDto;
import com.cat.S5._2.bookstack.entities.Role;
import com.cat.S5._2.bookstack.enums.UserRole;
import com.cat.S5._2.bookstack.services.UserService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
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
        public  ResponseEntity<List<UserDto>> getByRole(@RequestParam UserRole roleName){
        return ResponseEntity.ok(userService.findByRole(roleName));
            //Si la valeur ne correspond à aucun enum, Spring lève une MethodArgumentTypeMismatchException
        // AUTOMATIQUEMENT SANS AVOIR RIEN A FAIRE
        }

        @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.findDtoById(id));
        }

        @GetMapping("/{id}/books")
    public ResponseEntity<List<UserBookDto>> getUserBooksList(@PathVariable Long id){
        return ResponseEntity.ok(userService.getUserBooks(id));
        }

        @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody @Valid UpdateUserDto updateUserDto){
        return ResponseEntity.ok(userService.updateUser(id, updateUserDto));
        }

        @PatchMapping("{id}/password")
        @PreAuthorize("#id == principal.id or hasRole('ADMIN')")
    public ResponseEntity<Void> updatePassword(@PathVariable Long id,@RequestBody @Valid PasswordDto newPass){
            userService.updatePassword(id, newPass);
        return ResponseEntity.noContent().build();
        }

    @PreAuthorize("hasRole('ADMIN')")
      @DeleteMapping("{id}")
      public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
      }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/roles")
    public ResponseEntity<Void> addRole(
            @PathVariable Long id,
            @RequestBody @Valid RoleRequest roleRequest) {

        userService.addRoleToUser(id, roleRequest.role());
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}/roles/{role}")
    public ResponseEntity<Void> removeRole(
            @PathVariable Long id,
            @PathVariable UserRole role) {

        userService.removeRole(id, role);
        return ResponseEntity.noContent().build();
    }




    }
