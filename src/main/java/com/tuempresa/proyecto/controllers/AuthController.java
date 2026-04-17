package com.tuempresa.proyecto.controllers;

import com.tuempresa.proyecto.models.UserCredentials;
import com.tuempresa.proyecto.models.JwtResponse;
import com.tuempresa.proyecto.repository.UsuarioRepository; // IMPORTANTE
import com.tuempresa.proyecto.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder; // IMPORTANTE
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository userRepository; // Para guardar usuarios

    @Autowired
    private PasswordEncoder passwordEncoder; // Para encriptar la clave

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserCredentials loginRequest) {
        try {
            Authentication auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );
            String token = jwtUtil.generateToken(auth);
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Usuario o contraseña incorrectos");
        }
    }

    // --- NUEVO MÉTODO PARA EL PASO 1 (REGISTRO) ---
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserCredentials newUser) {
        // 1. Verificación de seguridad extra (aunque ya tienes el validador en el front)
        if (userRepository.existsByUsername(newUser.getUsername())) {
            return ResponseEntity.badRequest().body("Error: El usuario ya existe");
        }

        // 2. ENCRIPTAR la contraseña antes de guardar (OBLIGATORIO)
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        // 3. Guardar en la base de datos
        userRepository.save(newUser);

        return ResponseEntity.ok("Usuario registrado exitosamente");
    }
}