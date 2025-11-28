package com.primosjoyeria.backend.service;

import com.primosjoyeria.backend.dto.ProductoDto;
import com.primosjoyeria.backend.dto.ProductoRequest;
import com.primosjoyeria.backend.entity.Producto;
import com.primosjoyeria.backend.mapper.ProductoMapper;
import com.primosjoyeria.backend.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    // LISTAR
    public List<ProductoDto> listar() {
        return repo.findAll()
                .stream()
                .map(ProductoMapper::toDto)
                .toList();
    }

    // CREAR
    public ProductoDto crear(ProductoRequest request) {
        Producto p = new Producto();
        p.setNombre(request.getNombre());
        p.setPrecio(request.getPrecio());
        p.setImagenUrl(request.getImagenUrl()); // puede ser null

        Producto guardado = repo.save(p);
        return ProductoMapper.toDto(guardado);
    }

    // ACTUALIZAR
    public ProductoDto actualizar(Long id, ProductoRequest request) {
        Producto actual = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        actual.setNombre(request.getNombre());
        actual.setPrecio(request.getPrecio());
        actual.setImagenUrl(request.getImagenUrl());

        Producto actualizado = repo.save(actual);
        return ProductoMapper.toDto(actualizado);
    }

    // ELIMINAR
    public void eliminar(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Producto no encontrado");
        }
        repo.deleteById(id);
    }
}
