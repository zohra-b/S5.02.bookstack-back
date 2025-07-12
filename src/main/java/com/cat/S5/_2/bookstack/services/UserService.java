package com.cat.S5._2.bookstack.services;

import com.cat.S5._2.bookstack.dtos.user.UserDto;
import com.cat.S5._2.bookstack.entities.User;
import com.cat.S5._2.bookstack.mappers.UserMapper;
import com.cat.S5._2.bookstack.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;



    public UserDto findById(Long id){
        User user = userRepo.findById(id)
                .orElseThrow(()->new UsernameNotFoundException("User not found with id " + id));
        return userMapper.toUserDto(user);
    }

    public UserDto findByEmail(String email){
        User user =  userRepo.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("User not found with email " + email));
        return userMapper.toUserDto(user);
    }



}
