package com.primosjoyeria.backend.service;

import com.primosjoyeria.backend.dto.DolarDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class IndicadoresService {

    private final RestTemplate restTemplate = new RestTemplate();

    public DolarDto obtenerDolar() {
        String url = "https://mindicador.cl/api/dolar";

        // La respuesta es un JSON con un array "serie"
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        if (response == null || !response.containsKey("serie")) {
            throw new RuntimeException("No se pudo obtener el valor del dólar");
        }

        List<Map<String, Object>> serie = (List<Map<String, Object>>) response.get("serie");
        if (serie.isEmpty()) {
            throw new RuntimeException("No hay datos en la serie del dólar");
        }

        Map<String, Object> primerValor = serie.get(0);

        double valor = ((Number) primerValor.get("valor")).doubleValue();
        String fecha = (String) primerValor.get("fecha");

        return new DolarDto(valor, fecha);
    }
}