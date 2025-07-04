package com.example.demo;

public class Vehiculos {
    private String nombreApellidoIngreso;
    private String cedula;
    private String empresaGestionaPuesto;
    private String nombrePuesto;
    private String tipoVehiculo;
    private String numeroPlaca;
    private String fechaIngresoVehiculo;
    private String fechaSalidaVehiculo;
    private String observacion;

    public Vehiculos() {
    }

    public String getFechaIngresoVehiculo() {
        return fechaIngresoVehiculo;
    }

    public void setFechaIngresoVehiculo(String fechaIngresoVehiculo) {
        this.fechaIngresoVehiculo = fechaIngresoVehiculo;
    }

    public String getFechaSalidaVehiculo() {
        return fechaSalidaVehiculo;
    }

    public void setFechaSalidaVehiculo(String fechaSalidaVehiculo) {
        this.fechaSalidaVehiculo = fechaSalidaVehiculo;
    }

    public String getNombreApellidoIngreso() {
        return nombreApellidoIngreso;
    }

    public void setNombreApellidoIngreso(String nombreApellidoIngreso) {
        this.nombreApellidoIngreso = nombreApellidoIngreso;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEmpresaGestionaPuesto() {
        return empresaGestionaPuesto;
    }

    public void setEmpresaGestionaPuesto(String empresaGestionaPuesto) {
        this.empresaGestionaPuesto = empresaGestionaPuesto;
    }

    public String getNombrePuesto() {
        return nombrePuesto;
    }

    public void setNombrePuesto(String nombrePuesto) {
        this.nombrePuesto = nombrePuesto;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getNumeroPlaca() {
        return numeroPlaca;
    }

    public void setNumeroPlaca(String numeroPlaca) {
        this.numeroPlaca = numeroPlaca;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
