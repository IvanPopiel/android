package com.example.cd_market;

public class UserModelo {

    private String nombreUsuario, email;

    public UserModelo(String nombreUsuario, String email) {
        this.nombreUsuario = nombreUsuario;
        this.email = email;
    }

    public UserModelo() {
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void settNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
