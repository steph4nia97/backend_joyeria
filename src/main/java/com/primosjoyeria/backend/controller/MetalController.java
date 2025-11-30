package com.primosjoyeria.backend.controller;

import com.primosjoyeria.backend.dto.MetalPrecioDto;
import com.primosjoyeria.backend.service.MetalService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/metal")
public class MetalController {

    private final MetalService metalService;

    public MetalController(MetalService metalService) {
        this.metalService = metalService;
    }

    @GetMapping("/oro")
    public MetalPrecioDto getPrecioOro() {
        return metalService.obtenerPrecioOro();
    }
}