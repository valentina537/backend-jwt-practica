package com.tuempresa.proyecto.controllers;

import com.tuempresa.proyecto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/validators")
@CrossOrigin(origins = "*") // Para que Angular no tenga problemas de CORS
public class ValidationController {

    @Autowired
    private UsuarioRepository userRepository;

    // Este es el endpoint GENÉRICO que pide la consigna
    @GetMapping("/check-unique")
    public ResponseEntity<Boolean> checkUnique(@RequestParam String field, @RequestParam String value) {
        if ("username".equalsIgnoreCase(field)) {
            return ResponseEntity.ok(userRepository.existsByUsername(value));
        }
        // Como no tenemos otros campos, cualquier otra consulta devuelve false
        return ResponseEntity.ok(false);
    }

}