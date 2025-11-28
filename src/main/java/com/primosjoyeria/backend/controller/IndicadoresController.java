package com.primosjoyeria.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.primosjoyeria.backend.service.IndicadoresService;
import com.primosjoyeria.backend.dto.DolarDto;

@RestController
@RequestMapping("/api/indicadores")
public class IndicadoresController {

    private final IndicadoresService service;

    public IndicadoresController(IndicadoresService service) {
        this.service = service;
    }

    @GetMapping("/dolar")
    public ResponseEntity<DolarDto> obtenerDolar() {
        return ResponseEntity.ok(service.obtenerDolarActual());
    }
}