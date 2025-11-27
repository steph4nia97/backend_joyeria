package com.primosjoyeria.backend.controller;

import org.springframework.web.bind.annotation.*;
import com.primosjoyeria.backend.service.IndicadoresService;
import com.primosjoyeria.backend.dto.DolarDto;

@RestController
@RequestMapping("/api/indicadores")
@CrossOrigin(origins = "*") // permite consumo desde la app
public class IndicadoresController {

    private final IndicadoresService indicadoresService;

    public IndicadoresController(IndicadoresService indicadoresService) {
        this.indicadoresService = indicadoresService;
    }

    @GetMapping("/dolar")
    public DolarDto getDolarActual() {
        return indicadoresService.obtenerDolar();
    }
}
