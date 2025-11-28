package com.primosjoyeria.backend.dto;


import java.util.List;

public class MindicadorDolarResponse {

    private List<MindicadorDolarItem> serie;

    public List<MindicadorDolarItem> getSerie() {
        return serie;
    }

    public void setSerie(List<MindicadorDolarItem> serie) {
        this.serie = serie;
    }
}