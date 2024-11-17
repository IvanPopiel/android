package com.example.cd_market;

public class GameModelo {
    private int id;
    private String nombre;
    private String descripcion;

    // Constructor con parámetros
    public GameModelo(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    // Constructor sin parámetros (por si necesitas crear objetos vacíos)
    public GameModelo() {
        this.id = 0;
        this.nombre = "";
        this.descripcion = "";
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // Setters (si los necesitas)
    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}