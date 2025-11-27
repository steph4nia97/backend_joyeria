package com.primosjoyeria.backend.service;

import com.primosjoyeria.backend.entity.Producto;
import com.primosjoyeria.backend.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository repo;

    public ProductoService(ProductoRepository repo) {
        this.repo = repo;
    }

    public List<Producto> listar() {
        return repo.findAll();
    }

    public Producto buscar(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public Producto crear(Producto p) {
        return repo.save(p);
    }

    public Producto actualizar(Long id, Producto nuevo) {
        Producto actual = buscar(id);
        actual.setNombre(nuevo.getNombre());
        actual.setPrecio(nuevo.getPrecio());
        return repo.save(actual);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
