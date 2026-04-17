package com.tuempresa.proyecto.models;

import jakarta.persistence.*; // Importante para las anotaciones

@Entity // Le dice a Spring que esto es una tabla de la base de datos
@Table(name = "usuario")
public class UserCredentials {

    @Id // Define la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Se autoincrementa (1, 2, 3...)
    private Long id;

    private String username;
    private String password;

    // Constructores
    public UserCredentials() {}

    public UserCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}