package com.primosjoyeria.backend.service;

import com.primosjoyeria.backend.dto.DolarDto;
import com.primosjoyeria.backend.dto.MindicadorDolarItem;
import com.primosjoyeria.backend.dto.MindicadorDolarResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class IndicadoresService {

    private final RestTemplate restTemplate = new RestTemplate();

    public DolarDto obtenerDolarActual() {
        String url = "https://mindicador.cl/api/dolar";

        MindicadorDolarResponse response =
                restTemplate.getForObject(url, MindicadorDolarResponse.class);

        if (response == null || response.getSerie() == null || response.getSerie().isEmpty()) {
            throw new IllegalStateException("Respuesta inválida desde mindicador.cl");
        }

        MindicadorDolarItem item = response.getSerie().get(0);

        return new DolarDto(
                item.getValor(),
                item.getFecha()
        );
    }

}