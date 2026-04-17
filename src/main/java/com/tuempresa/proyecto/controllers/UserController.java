package com.tuempresa.proyecto.controllers;

import com.tuempresa.proyecto.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // Permite la conexión desde el VS Code
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/perfil")
    public Map<String, String> getPerfil(@RequestHeader("Authorization") String token) {
        // El token viene como "Bearer xxxxx.yyyyy.zzzzz", quitamos "Bearer "
        String jwt = token.substring(7);

        // Extraemos el nombre para demostrar que el token funciona
        //String username = jwtUtil.extractUsername(jwt);
        // Cambia esta línea en tu UserController:
        String username = jwtUtil.getUsernameFromToken(jwt);

        Map<String, String> response = new HashMap<>();
        response.put("mensaje", "¡Bienvenido a la zona protegida!");
        response.put("usuarioLogueado", username);
        response.put("estado", "Éxito: Token válido");

        return response;
    }
}