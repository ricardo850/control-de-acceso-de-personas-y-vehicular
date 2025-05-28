package com.example.demo;

public class BaseDatosCliente {
    private String nombreBaseDatos;
    private String contrasenaBaseDatos;

    public BaseDatosCliente() {
    }

    public BaseDatosCliente(String contrasenaBaseDatos, String nombreBaseDatos) {
        this.contrasenaBaseDatos = contrasenaBaseDatos;
        this.nombreBaseDatos = nombreBaseDatos;
    }

    public String getNombreBaseDatos() {
        return nombreBaseDatos;
    }

    public void setNombreBaseDatos(String nombreBaseDatos) {
        this.nombreBaseDatos = nombreBaseDatos;
    }

    public String getContrasenaBaseDatos() {
        return contrasenaBaseDatos;
    }

    public void setContrasenaBaseDatos(String contrasenaBaseDatos) {
        this.contrasenaBaseDatos = contrasenaBaseDatos;
    }
}
