package com.example.demo;

public class InicioSesion {
    private String nombreEmpresaVisitada;
    private String contrasenaEmpresa;

    public InicioSesion() {
    }

    public InicioSesion(String nombreEmpresaVisitada, String puestoTrabajo, String contrasenaEmpresa) {
        this.nombreEmpresaVisitada = nombreEmpresaVisitada;
        this.contrasenaEmpresa = contrasenaEmpresa;
    }


    public String getNombreEmpresaVisitada() {
        return nombreEmpresaVisitada;
    }

    public void setNombreEmpresaVisitada(String nombreEmpresaVisitada) {
        this.nombreEmpresaVisitada = nombreEmpresaVisitada;
    }

    public String getContrasenaEmpresa() {
        return contrasenaEmpresa;
    }

    public void setContrasenaEmpresa(String contrasenaEmpresa) {
        this.contrasenaEmpresa = contrasenaEmpresa;
    }
}
