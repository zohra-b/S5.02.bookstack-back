package com.cat.S5._2.bookstack.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {  //ce filtre est appliqué une seule fois par requête.
    private final JwtService jwtService;  //pour lire et vérifier le contenu du jeton JWT.
    private final UserDetailsService userDetailsService; //pour récupérer les détails de l’utilisateur à partir de son email ou nom d’utilisateur. //NATIVE SPRING

    public JwtAuthFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(  //Cette méthode est appelée automatiquement pour chaque requête HTTP qui entre dans ton application
                                      @NonNull HttpServletRequest request,  // la requête contient toutes les infos utiles : l'URL demandée/les headers (comme le Authorization)/les paramètres/les cookies, etc.
                                      @NonNull HttpServletResponse response, //  réponse que ton application va envoyer au client. et qu'il faut configurer
                                      @NonNull FilterChain filterChain // c'est la serie de filtre qu'on veut appliquer : filterChain.doFilter(request, response)
    ) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization"); //récupère le jeton JWT depuis l'en-tête Authorization de la requête
        final String jwt;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);  // Si aucun jeton n’est fourni ou s’il est mal formé, on laisse passer la requête sans authentification.
            return;
        }

        jwt = authHeader.substring(7); // on extrait le token : on enlève "Bearer " (7 caractères) pour ne garder que le token lui-même.
        userEmail = jwtService.extractUserName(jwt); // lit le nom d’utilisateur ou email à l’intérieur du JWT : metgode dans JwtService, classe à créer

        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) { //S’il y a bien un utilisateur et qu’il n’est pas déjà connecté, on continue.
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail); //native Spring : récupère toutes les infos sur cet utilisateur (rôles, mots de passe, etc.)

            if (jwtService.isTokenValid(jwt, userDetails)) { // vérifie que le token est valide (pas expiréni modifé etc)

                //la liste des rôles de l'utilisateur peut ne pas avoir le préfixe "ROLE_" dans certains cas,
                // donc on s'assure qu'ils sont tous correctement formatés. Car Spring Security attend que les rôles commencent par "ROLE_".
                List<GrantedAuthority> authorities = userDetails.getAuthorities().stream()
                        .map(authority -> {
                            String role = authority.getAuthority();
                            if (!role.startsWith("ROLE_")) {
                                return new SimpleGrantedAuthority("ROLE_" + role); // Add prefix if missing
                            }
                            return authority; // Keep as-is if already prefixed
                        })
                        .toList();

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken( //Crée un objet d’authentification, un "badge d'accès" pour cet utilisateur, avec ses rôles.
                        userDetails,
                        null,
                        authorities    );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)   ); // Lie cette authentification à la requête en cours
                SecurityContextHolder.getContext().setAuthentication(authenticationToken); // msg à Spring Security : "cet utilisateur est maintenant connecté et autorisé"
            }

            filterChain.doFilter(request, response); //après le traitement du filtre, on continue normalement vers le contrôleur ou service demandé.
        }
    }
}
