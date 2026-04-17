package com.tuempresa.proyecto.repository;

import com.tuempresa.proyecto.models.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UserCredentials, Long> {

    // Este ya lo tenías para el Login
    Optional<UserCredentials> findByUsername(String username);

    // MODIFICACIÓN PARA LA PRÁCTICA:
    // Este es el método eficiente que pide el profe (existsBy...)
    boolean existsByUsername(String username);

    // BORRÉ la línea de existsByEmail porque no tenés ese campo en la DB
}