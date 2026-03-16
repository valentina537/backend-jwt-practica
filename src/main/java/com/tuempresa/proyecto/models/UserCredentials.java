package com.tuempresa.proyecto.models;

public class UserCredentials {
    private String username;
    private String password;

    // Constructores
    public UserCredentials() {}

    public UserCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters y Setters (Importante para que Spring lea el JSON)
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}