package com.tuempresa.proyecto.security;

import com.tuempresa.proyecto.models.UserCredentials;
import com.tuempresa.proyecto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscamos el usuario en la DB
        UserCredentials usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // Le damos a Spring Security el usuario con su clave para que él la compare
        return User.withUsername(usuario.getUsername())
                .password("{noop}" + usuario.getPassword()) // {noop} se usa si la clave no está encriptada
                .roles("USER")
                .build();
    }
}