package com.primosjoyeria.backend.mapper;

import com.primosjoyeria.backend.dto.ProductoDto;
import com.primosjoyeria.backend.entity.Producto;

public class ProductoMapper {

    public static ProductoDto toDto(Producto p) {
        if (p == null) return null;
        return new ProductoDto(
                p.getId(),
                p.getNombre(),
                p.getPrecio(),
                p.getImagenUrl()
        );
    }
}
