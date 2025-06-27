package com.example.demo;

public class InicioSesion {
    private String correo;
    private String contrasena;

    public InicioSesion() {
    }

    public InicioSesion(String nombreEmpresaVisitada, String puestoTrabajo, String contrasenaEmpresa) {
        this.correo = nombreEmpresaVisitada;
        this.contrasena = contrasenaEmpresa;
    }


    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
