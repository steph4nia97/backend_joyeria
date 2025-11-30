package com.primosjoyeria.backend.dto;

public class MetalPrecioDto {

    private String metal;
    private double precioOnzaClp;
    private double precioGramoClp;
    private String fecha;

    public MetalPrecioDto(String metal, double precioOnzaClp, double precioGramoClp, String fecha) {
        this.metal = metal;
        this.precioOnzaClp = precioOnzaClp;
        this.precioGramoClp = precioGramoClp;
        this.fecha = fecha;
    }

    public String getMetal() { return metal; }
    public double getPrecioOnzaClp() { return precioOnzaClp; }
    public double getPrecioGramoClp() { return precioGramoClp; }
    public String getFecha() { return fecha; }
}
