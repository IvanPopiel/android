package com.example.cd_market;

public class UserModelo {
    private int id;
    private String nombreUsuario;
    private String email;

    // Constructor
    public UserModelo(int id, String nombreUsuario, String email) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
    }

    public UserModelo() {
    }

    public int getId() {
        return id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getEmail() {
        return email;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
