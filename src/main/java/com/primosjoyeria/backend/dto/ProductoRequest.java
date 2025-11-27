package com.primosjoyeria.backend.dto;

public class ProductoRequest {

    private String nombre;
    private Integer precio;

    public ProductoRequest() {}

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getPrecio() { return precio; }
    public void setPrecio(Integer precio) { this.precio = precio; }
}