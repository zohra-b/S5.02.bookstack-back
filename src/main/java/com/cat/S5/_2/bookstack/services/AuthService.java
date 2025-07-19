package com.cat.S5._2.bookstack.services;

import com.cat.S5._2.bookstack.dtos.auth.LoginRequest;
import com.cat.S5._2.bookstack.dtos.auth.RegisterRequest;
import com.cat.S5._2.bookstack.dtos.auth.TokenResponse;
import com.cat.S5._2.bookstack.entities.User;
import com.cat.S5._2.bookstack.enums.UserRole;
import com.cat.S5._2.bookstack.exceptions.EmailAlreadyExistsException;
import com.cat.S5._2.bookstack.repositories.UserRepository;
import com.cat.S5._2.bookstack.security.JwtService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor // This annotation generates a constructor with required arguments for all final fields
public class AuthService {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder; //Interface de Spring Security : Hashage sécurisé des mots de passe (ex: avec BCrypt, evite stockage en clair dans la bd.
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;  //Interface cœur de Spring Security : Valide les credentials (email/mot de passe) lors du login
                                                                                                    // Délègue à UserDetailsService (chargé depuis UserRepository)
                                                                                                    //Lance une exception (BadCredentialsException) si échec
@Transactional
    public TokenResponse register(RegisterRequest registerRequest) {
        if (userRepo.existsByEmail(registerRequest.getEmail())) {
            throw new EmailAlreadyExistsException(registerRequest.getEmail());
        }

        User user = User.builder()
                .userName(registerRequest.getUserName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(UserRole.ROLE_USER)
                .build();

        User savedUser = userRepo.save(user);
        String jwtToken =  jwtService.generateToken(savedUser);
        return new TokenResponse(jwtToken, savedUser.getUserId());
    }

    public TokenResponse login(LoginRequest loginRequest){
       authenticationManager.authenticate(  // verifie que l email existe et que le pass est bon sinon renvoie une exception BadCredentialsException, tout automatique !
               new UsernamePasswordAuthenticationToken(
                          loginRequest.getEmail(),
                          loginRequest.getPassword()
               )
       );
        User user = userRepo.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + loginRequest.getEmail()));

        String jwtToken =  jwtService.generateToken(user); // Génère un token JWT pour l'utilisateur authentifié
        return new TokenResponse(jwtToken, user.getUserId());
    }

}
