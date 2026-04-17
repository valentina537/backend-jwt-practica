package com.tuempresa.proyecto.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;
@Component
public class JwtUtil {

    // Usamos la clave larga que ya tenías
    private String secret = "EstaEsUnaClaveSecretaMuyLargaYSeguraParaMiPractica2024!";

    // Tiempo de vida: 1 día (86400000 milisegundos)
    private int expiration = 86400000;

    // GENERAR TOKEN (Versión Clase 4)
    public String generateToken(Authentication auth) {
        UserDetails userPrincipal = (UserDetails) auth.getPrincipal();

        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes())) // Forma moderna de firmar
                .compact();
    }

    // VALIDAR TOKEN
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // EXTRAER USUARIO
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}