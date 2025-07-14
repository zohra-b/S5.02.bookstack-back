package com.cat.S5._2.bookstack.services;

import com.cat.S5._2.bookstack.dtos.book.BookDto;
import com.cat.S5._2.bookstack.dtos.user.UserDto;
import com.cat.S5._2.bookstack.dtos.userbook.UserBookDto;
import com.cat.S5._2.bookstack.entities.Book;
import com.cat.S5._2.bookstack.entities.Role;
import com.cat.S5._2.bookstack.entities.User;
import com.cat.S5._2.bookstack.entities.UserBook;
import com.cat.S5._2.bookstack.enums.UserRole;
import com.cat.S5._2.bookstack.mappers.UserBookMapper;
import com.cat.S5._2.bookstack.mappers.UserMapper;
import com.cat.S5._2.bookstack.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepo;
    private final UserMapper userMapper;
    private final UserBookMapper userBookMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;


    public List<UserDto> findAll(){
        return userRepo.findAll().stream()
                .map(userMapper::toUserDto)
                .sorted(Comparator.comparing(UserDto::userName))
                .toList();
    }

    public User findById(Long id){
        return userRepo.findById(id)
                .orElseThrow(()->new UsernameNotFoundException("User not found with id " + id));
    }

    public UserDto findByEmail(String email){
        User user =  userRepo.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("User not found with email " + email));
        return userMapper.toUserDto(user);
    }

    public List<UserDto> findByRole(Role role){
        return userRepo.findByRole(role).stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    public UserDto updateUser(Long id, UserDto userDto){
        User existingUser = findById(id);
        existingUser.setUserName(userDto.userName());
        User updatedUser = userRepo.save(existingUser);
        return userMapper.toUserDto(updatedUser);
    }

    public void updatePassword(Long id, String newPassword){
        User user = findById(id);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);
    }
    public void deleteUser(Long id){
        User usertoDelete =findById(id);
        userRepo.delete(usertoDelete);
    }

    public void addRoleToUser(Long id, UserRole userRole){
        User user = findById(id);
        Role newRole = roleService.findByName(userRole);
        user.addRole(newRole);
        userRepo.save(user);
    }

    public void removeRole(Long id, UserRole userRole){
        User user = findById(id);
        Role role = roleService.findByName(userRole);
        user.removeRole(role);
        userRepo.save(user);
    }

    public boolean emailExists(String email) {
        return userRepo.existsByEmail(email);
    }

    public List<UserBookDto> getUserBooks(Long id){
        User user = findById(id);
        return user.getUserBooksList().stream()
                .map(userBookMapper::toDto)
                .toList();
    }


}
