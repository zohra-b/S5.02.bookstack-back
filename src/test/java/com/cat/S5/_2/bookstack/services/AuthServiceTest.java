package com.cat.S5._2.bookstack.services;

import com.cat.S5._2.bookstack.dtos.auth.LoginRequest;
import com.cat.S5._2.bookstack.dtos.auth.RegisterRequest;
import com.cat.S5._2.bookstack.entities.User;
import com.cat.S5._2.bookstack.exceptions.EmailAlreadyExistsException;
import com.cat.S5._2.bookstack.repositories.UserRepository;
import com.cat.S5._2.bookstack.security.JwtService;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // This annotation allows the use of Mockito in JUnit 5 tests
class AuthServiceTest {
    @Mock
    private UserRepository userRepo;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private JwtService jwtService;
    @Mock
    private AuthenticationManager authenticationManager;
    @InjectMocks
    private AuthService authService;

    @Test
    void testRegister_returnsToken() {
        // Arrange
        RegisterRequest request = new RegisterRequest("Zohra", "zohra@me.com", "123456");

        when(userRepo.existsByEmail(request.getEmail())).thenReturn(false);
        when(passwordEncoder.encode(request.getPassword())).thenReturn("hashed_password");
        when(userRepo.save(any(User.class)))
                .thenAnswer(inv -> inv.getArgument(0));
        when(jwtService.generateToken(any(User.class)))
                .thenReturn("mocked_jwt_token");

        // Act
        String token = authService.register(request);

        // Assert
        assertNotNull(token);
        assertEquals("mocked_jwt_token", token);

        // Verify interactions
        verify(userRepo).existsByEmail(request.getEmail());
        verify(userRepo).save(any(User.class));
        verify(jwtService).generateToken(any(User.class));
    }

    @Test
    void testLogin_returnsToken() {
        // Arrange
        LoginRequest request = new LoginRequest("zohra@me.com", "123456");

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUserName("Zohra");
        user.setPassword("hashed_password");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null); // Simulate successful authentication car retourne null (sinon aurait lancé une exception)
        when(userRepo.findByEmail(request.getEmail())).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("mocked_jwt_token");

        // Act
        String token = authService.login(request);

        // Assert
        assertNotNull(token, "Token should not be null");
        assertEquals("mocked_jwt_token", token, "Token should match the mocked value");
        // Verify interactions
        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(userRepo).findByEmail(request.getEmail());
        verify(jwtService).generateToken(user);
    }

    @Test
    void testLogin_withInvalidCredentials_throwsException() {
        // Arrange
        LoginRequest request = new LoginRequest("zohra@me.com", "wrong_password");
        User user = new User();
        user.setEmail("zohra@me.com");
        user.setUserName("Zohra");
        user.setPassword("hashed_password");

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Bad credential"));

        //Assert
        assertThrows(BadCredentialsException.class, ()->{authService.login(request);}); // assertThrows(la classe de l'exception, ()->la methode_qui_est_censée_planter())

    }

    @Test
    void testRegisterWithExistingEmail_throwsException(){
        //Arrange
        RegisterRequest request = new RegisterRequest("Zohra", "zohra@me.com", "123456");



        when(userRepo.existsByEmail(request.getEmail()))
                .thenReturn(true);

        assertThrows(EmailAlreadyExistsException.class, ()->authService.register(request));

        verify(userRepo).existsByEmail("zohra@me.com");
        verify(userRepo, never()).save(any());
        verifyNoInteractions(jwtService);
    }
}
