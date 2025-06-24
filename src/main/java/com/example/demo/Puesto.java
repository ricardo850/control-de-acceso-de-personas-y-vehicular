package com.example.demo;

public class Puesto {
    private String nombreBaseDatosDatos;
    private String puesto;

    public Puesto(String nombreBaseDatosDatos, String puesto) {
        this.nombreBaseDatosDatos = nombreBaseDatosDatos;
        this.puesto = puesto;
    }

    public String getNombreBaseDatosDatos() {
        return nombreBaseDatosDatos;
    }

    public void setNombreBaseDatosDatos(String nombreBaseDatosDatos) {
        this.nombreBaseDatosDatos = nombreBaseDatosDatos;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
}
