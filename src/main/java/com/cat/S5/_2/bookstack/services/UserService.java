package com.cat.S5._2.bookstack.services;

import com.cat.S5._2.bookstack.dtos.user.PasswordDto;
import com.cat.S5._2.bookstack.dtos.user.UpdateUserDto;
import com.cat.S5._2.bookstack.dtos.user.UserDto;
import com.cat.S5._2.bookstack.dtos.userbook.UserBookDto;
import com.cat.S5._2.bookstack.entities.User;
import com.cat.S5._2.bookstack.enums.UserRole;
import com.cat.S5._2.bookstack.mappers.UserBookMapper;
import com.cat.S5._2.bookstack.mappers.UserMapper;
import com.cat.S5._2.bookstack.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepo;
    private final UserMapper userMapper;
    private final UserBookMapper userBookMapper;
    private final PasswordEncoder passwordEncoder;



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
    public UserDto findDtoById(Long id) {
        User user = findById(id);
        return userMapper.toUserDto(user);
    }

    public UserDto findByEmail(String email){
        User user =  userRepo.findByEmail(email)
                .orElseThrow(()->new UsernameNotFoundException("User not found with email " + email));
        return userMapper.toUserDto(user);
    }

    public List<UserDto> findByRole(UserRole userRole){
        return userRepo.findByRole(userRole).stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    @Transactional
    public UserDto updateUser(Long id, UpdateUserDto updateUserDto){
        User user = findById(id);
        if (updateUserDto.userName() != null)
            user.setUserName(updateUserDto.userName());
        if (updateUserDto.email() != null)
            user.setEmail(updateUserDto.email());

        User updatedUser = userRepo.save(user);
        return userMapper.toUserDto(updatedUser);
    }

    @Transactional
    public void updatePassword(Long id, PasswordDto newPassword){
        User user = findById(id);
        user.setPassword(passwordEncoder.encode(newPassword.newPassword()));
        userRepo.save(user);
    }

    @Transactional
    public void deleteUser(Long id){
        User user =findById(id);
        userRepo.delete(user);
    }

    @Transactional
    public UserDto toggleUserRole(Long userId) {
        User user = findById(userId);
        user.toggleRole();
        User updatedUser = userRepo.save(user);
        return userMapper.toUserDto(updatedUser);
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



    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        List<GrantedAuthority> authorities = List.of(
                new SimpleGrantedAuthority(user.getRole().name())
        );

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}
