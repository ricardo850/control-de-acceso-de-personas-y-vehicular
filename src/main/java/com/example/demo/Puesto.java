package com.example.demo;

public class Puesto {
    private String nombreBaseDatosDatos;
    private String nombrepuesto;
    private String contrasena;
    private String ubicacionPuesto;
    private String fechaCreacionPuesto;


    public Puesto() {
    }

    public String getUbicacionPuesto() {
        return ubicacionPuesto;
    }

    public void setUbicacionPuesto(String ubicacionPuesto) {
        this.ubicacionPuesto = ubicacionPuesto;
    }

    public String getFechaCreacionPuesto() {
        return fechaCreacionPuesto;
    }

    public void setFechaCreacionPuesto(String fechaCreacionPuesto) {
        this.fechaCreacionPuesto = fechaCreacionPuesto;
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
