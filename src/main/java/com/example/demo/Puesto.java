package com.example.demo;

public class Puesto {
    private String nombreBaseDatosDatos;
    private String nombrepuesto;
    private String contrasena;

    public Puesto(String nombreBaseDatosDatos, String puesto,String contrasena) {
        this.nombreBaseDatosDatos = nombreBaseDatosDatos;
        this.nombrepuesto = puesto;
        this.contrasena = contrasena;

    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreBaseDatosDatos() {
        return nombreBaseDatosDatos;
    }

    public void setNombreBaseDatosDatos(String nombreBaseDatosDatos) {
        this.nombreBaseDatosDatos = nombreBaseDatosDatos;
    }

    public String getNombrepuesto() {
        return nombrepuesto;
    }

    public void setNombrepuesto(String nombrepuesto) {
        this.nombrepuesto = nombrepuesto;
    }
}
