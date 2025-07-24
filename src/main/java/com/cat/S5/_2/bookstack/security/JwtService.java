package com.cat.S5._2.bookstack.security;


import com.cat.S5._2.bookstack.security.exceptions.*;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtService {
    @Value("${jwt.secret.key}")
    private String secret;

    private SecretKey getSignInKey() {
        byte[] keyBytes = Base64.getDecoder().decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token){ //récupére l'identifiant principal du token
        return extractClaim(token, Claims::getSubject);
        // en JWT (JSON Web Token), Claims : informations contenues dans le token; paire clé/valeur stockée dans un JWT
        //getSubject champ standard dans les JWT
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        //Function<Claims, T> claimsResolver
        //C’est une fonction (ou un "comportement" qu’on passe en paramètre) qui prend en entrée un objet Claims
        // renvoie un type T (getSubject() = string nom ou getExpiration() = date
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
        ){
        return Jwts  //jsonwebtoken
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60))
                .signWith(getSignInKey(), Jwts.SIG.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userName = extractUserName(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        try {
            return Jwts
                    .parser()
                    .verifyWith(getSignInKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            throw new TokenExpiredException("Token expired at " + e.getClaims().getExpiration(), e);
        } catch (MalformedJwtException | IllegalArgumentException e) {
            throw new TokenMalformedJwtException(e);
        } catch (UnsupportedJwtException e){
            throw new TokenUnsupportedException(e);
        } catch (SecurityException e) {
            throw new TokenSignatureException(e);
        } catch (JwtException e) {
            throw new JwtExceptions("JWT processing failed", e);
        }
    }
}
