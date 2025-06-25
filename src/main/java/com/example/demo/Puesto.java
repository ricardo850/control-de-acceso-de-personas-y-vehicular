package com.example.demo;

public class Puesto {
    private String nombreBaseDatosDatos;
    private String nombrepuesto;

    public Puesto(String nombreBaseDatosDatos, String puesto) {
        this.nombreBaseDatosDatos = nombreBaseDatosDatos;
        this.nombrepuesto = puesto;
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
