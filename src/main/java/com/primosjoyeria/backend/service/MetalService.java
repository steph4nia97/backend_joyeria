package com.primosjoyeria.backend.service;

import com.primosjoyeria.backend.dto.MetalsDevResponse;
import com.primosjoyeria.backend.dto.MetalPrecioDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;

@Service
public class MetalService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${metals.api.key}")
    private String apiKey;

    public MetalPrecioDto obtenerPrecioOro() {

        String url = UriComponentsBuilder
                .fromHttpUrl("https://api.metals.dev/v1/metal/spot")
                .queryParam("api_key", apiKey)
                .queryParam("metal", "gold")
                .queryParam("currency", "CLP")
                .toUriString();

        MetalsDevResponse resp =
                restTemplate.getForObject(url, MetalsDevResponse.class);

        if (resp == null || resp.getRate() == null) {
            throw new RuntimeException("Error obteniendo valor del oro");
        }

        double onzaClp = resp.getRate().getPrice();   // precio onza CLP
        double gramoClp = onzaClp / 31.1035;          // conversion

        return new MetalPrecioDto(
                "ORO",
                onzaClp,
                gramoClp,
                LocalDate.now().toString()
        );
    }
}
