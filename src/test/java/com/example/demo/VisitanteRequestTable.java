package com.example.demo;

public class VisitanteRequestTable {
    private String NombreApellidos;
    private int cedula;
    private String EmpresaPermiso;
    private String NombrePuesto;
    private String TipoSangre;
    private String NombreApellidosEmergencia;
    private String TelefonoEmergencia;
    private String Eps;
    private String Arl;
    private String FuncionarioGestionaVisita;
    private String TraeComputoExterno;
    private String MarcaEquipo;
    private String Serialequipo;

    public VisitanteRequestTable() {
    }

    public VisitanteRequestTable(String nombreApellidos, int cedula, String empresaPermiso, String nombrePuesto,
                                 String tipoSangre, String nombreApellidosEmergencia, String telefonoEmergencia,
                                 String eps, String arl, String funcionarioGestionaVisita,
                                 String traeComputoExterno, String marcaEquipo, String serialequipo) {
        this.NombreApellidos = nombreApellidos;
        this.cedula = cedula;
        this.EmpresaPermiso = empresaPermiso;
        this.NombrePuesto = nombrePuesto;
        this.TipoSangre = tipoSangre;
        this.NombreApellidosEmergencia = nombreApellidosEmergencia;
        this.TelefonoEmergencia = telefonoEmergencia;
        this.Eps = eps;
        this.Arl = arl;
        this.FuncionarioGestionaVisita = funcionarioGestionaVisita;
        this.TraeComputoExterno = traeComputoExterno;
        this.MarcaEquipo = marcaEquipo;
        this.Serialequipo = serialequipo;
    }

    public String getTipoSangre() {
        return TipoSangre;
    }

    public void setTipoSangre(String tipoSangre) {
        this.TipoSangre = tipoSangre;
    }

    public String getNombreApellidos() {
        return NombreApellidos;
    }

    public void setNombreApellidos(String nombreApellidos) {
        this.NombreApellidos = nombreApellidos;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getEmpresaPermiso() {
        return EmpresaPermiso;
    }

    public void setEmpresaPermiso(String empresaPermiso) {
        this.EmpresaPermiso = empresaPermiso;
    }

    public String getNombrePuesto() {
        return NombrePuesto;
    }

    public void setNombrePuesto(String nombrePuesto) {
        this.NombrePuesto = nombrePuesto;
    }

    public String getNombreApellidosEmergencia() {
        return NombreApellidosEmergencia;
    }

    public void setNombreApellidosEmergencia(String nombreApellidosEmergencia) {
        this.NombreApellidosEmergencia = nombreApellidosEmergencia;
    }

    public String getTelefonoEmergencia() {
        return TelefonoEmergencia;
    }

    public void setTelefonoEmergencia(String telefonoEmergencia) {
        this.TelefonoEmergencia = telefonoEmergencia;
    }

    public String getEps() {
        return Eps;
    }

    public void setEps(String eps) {
        this.Eps = eps;
    }

    public String getArl() {
        return Arl;
    }

    public void setArl(String arl) {
        this.Arl = arl;
    }

    public String getFuncionarioGestionaVisita() {
        return FuncionarioGestionaVisita;
    }

    public void setFuncionarioGestionaVisita(String funcionarioGestionaVisita) {
        this.FuncionarioGestionaVisita = funcionarioGestionaVisita;
    }

    public String getTraeComputoExterno() {
        return TraeComputoExterno;
    }

    public void setTraeComputoExterno(String traeComputoExterno) {
        this.TraeComputoExterno = traeComputoExterno;
    }

    public String getMarcaEquipo() {
        return MarcaEquipo;
    }

    public void setMarcaEquipo(String marcaEquipo) {
        this.MarcaEquipo = marcaEquipo;
    }

    public String getSerialequipo() {
        return Serialequipo;
    }

    public void setSerialequipo(String serialequipo) {
        this.Serialequipo = serialequipo;
    }
}
