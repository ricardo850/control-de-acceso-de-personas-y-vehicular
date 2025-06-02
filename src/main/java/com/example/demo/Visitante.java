package com.example.demo;

public class Visitante {

    private String nombreApellidos;
    private int cedula;
    private String empresaGestionaPuesto;
    private String empresaPermiso;
    private String nombrePuesto;
    private String tipoSangre;
    private String nombreApellidosEmergencia;
    private String telefonoEmergencia;
    private String eps;
    private String arl;
    private String funcionarioGestionaVisita;
    private String traeComputoExterno;
    private String marcaEquipo;
    private String serialEquipo;

    public Visitante() {
    }

    public Visitante(String nombreApellidos, String traeComputoExterno, String funcionarioGestionaVisita, String serialEquipo,
                     String marcaEquipo, String arl, String eps, String telefonoEmergencia, String nombreApellidosEmergencia,
                     String tipoSangre, String nombrePuesto, String empresaPermiso, String empresaGestionaPuesto, int cedula) {
        this.nombreApellidos = nombreApellidos;
        this.traeComputoExterno = traeComputoExterno;
        this.funcionarioGestionaVisita = funcionarioGestionaVisita;
        this.serialEquipo = serialEquipo;
        this.marcaEquipo = marcaEquipo;
        this.arl = arl;
        this.eps = eps;
        this.telefonoEmergencia = telefonoEmergencia;
        this.nombreApellidosEmergencia = nombreApellidosEmergencia;
        this.tipoSangre = tipoSangre;
        this.nombrePuesto = nombrePuesto;
        this.empresaPermiso = empresaPermiso;
        this.empresaGestionaPuesto = empresaGestionaPuesto;
        this.cedula = cedula;
    }

    public String getNombreApellidos() {
        return nombreApellidos;
    }

    public void setNombreApellidos(String nombreApellidos) {
        this.nombreApellidos = nombreApellidos;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getEmpresaGestionaPuesto() {
        return empresaGestionaPuesto;
    }

    public void setEmpresaGestionaPuesto(String empresaGestionaPuesto) {
        this.empresaGestionaPuesto = empresaGestionaPuesto;
    }

    public String getEmpresaPermiso() {
        return empresaPermiso;
    }

    public void setEmpresaPermiso(String empresaPermiso) {
        this.empresaPermiso = empresaPermiso;
    }

    public String getNombrePuesto() {
        return nombrePuesto;
    }

    public void setNombrePuesto(String nombrePuesto) {
        this.nombrePuesto = nombrePuesto;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getNombreApellidosEmergencia() {
        return nombreApellidosEmergencia;
    }

    public void setNombreApellidosEmergencia(String nombreApellidosEmergencia) {
        this.nombreApellidosEmergencia = nombreApellidosEmergencia;
    }

    public String getTelefonoEmergencia() {
        return telefonoEmergencia;
    }

    public void setTelefonoEmergencia(String telefonoEmergencia) {
        this.telefonoEmergencia = telefonoEmergencia;
    }

    public String getEps() {
        return eps;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getArl() {
        return arl;
    }

    public void setArl(String arl) {
        this.arl = arl;
    }

    public String getFuncionarioGestionaVisita() {
        return funcionarioGestionaVisita;
    }

    public void setFuncionarioGestionaVisita(String funcionarioGestionaVisita) {
        this.funcionarioGestionaVisita = funcionarioGestionaVisita;
    }

    public String getTraeComputoExterno() {
        return traeComputoExterno;
    }

    public void setTraeComputoExterno(String traeComputoExterno) {
        this.traeComputoExterno = traeComputoExterno;
    }

    public String getMarcaEquipo() {
        return marcaEquipo;
    }

    public void setMarcaEquipo(String marcaEquipo) {
        this.marcaEquipo = marcaEquipo;
    }

    public String getSerialEquipo() {
        return serialEquipo;
    }

    public void setSerialEquipo(String serialEquipo) {
        this.serialEquipo = serialEquipo;
    }
}
