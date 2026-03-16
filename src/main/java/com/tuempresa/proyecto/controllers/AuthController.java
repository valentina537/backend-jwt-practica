package com.tuempresa.proyecto.controllers;

import com.tuempresa.proyecto.models.UserCredentials;
import com.tuempresa.proyecto.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*") // Esto permite que tu HTML se conecte sin bloqueos
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody UserCredentials credentials) {
        // Simulación de validación (En una app real buscarías en DB)
        if ("admin".equals(credentials.getUsername()) && "1234".equals(credentials.getPassword())) {
            String token = jwtUtil.generateToken(credentials.getUsername());

            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return response;
        } else {
            throw new RuntimeException("Credenciales inválidas");
        }
    }
}