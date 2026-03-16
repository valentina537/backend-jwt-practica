package com.tuempresa.proyecto.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component
public class JwtUtil {
    // Usamos la clave que definiste en el properties o una fija aquí
    private String secret = "EstaEsUnaClaveSecretaMuyLargaYSeguraParaMiPractica2024!";

    // Método para crear el token (El Sello)
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // Dura 1 hora
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    // Método para extraer el nombre del usuario del token
    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // Método para validar si el token es legítimo
    public boolean validateToken(String token, String username) {
        final String extractedUser = extractUsername(token);
        return (extractedUser.equals(username) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }
}