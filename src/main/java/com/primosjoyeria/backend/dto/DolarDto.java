package com.primosjoyeria.backend.dto;



public class DolarDto {
    private double valor;
    private String fecha;

    public DolarDto(double valor, String fecha) {
        this.valor = valor;
        this.fecha = fecha;
    }

    public double getValor() {
        return valor;
    }

    public String getFecha() {
        return fecha;
    }
}

