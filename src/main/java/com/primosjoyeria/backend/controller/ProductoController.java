package com.primosjoyeria.backend.controller;

import com.primosjoyeria.backend.dto.ProductoRequest;
import com.primosjoyeria.backend.entity.Producto;
import com.primosjoyeria.backend.repository.ProductoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoRepository productoRepository;

    public ProductoController(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    // Listar todos (clientes y admin)
    @GetMapping
    public List<Producto> listar() {
        return productoRepository.findAll();
    }

    // Crear producto (admin)
    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody ProductoRequest request) {
        Producto producto = new Producto();
        producto.setNombre(request.getNombre());
        producto.setPrecio(request.getPrecio());

        Producto guardado = productoRepository.save(producto);
        return ResponseEntity.ok(guardado);
    }

    // Actualizar producto (admin)
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody ProductoRequest request) {
        return productoRepository.findById(id)
                .map(prod -> {
                    prod.setNombre(request.getNombre());
                    prod.setPrecio(request.getPrecio());
                    Producto actualizado = productoRepository.save(prod);
                    return ResponseEntity.ok(actualizado);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Eliminar producto (admin)
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (!productoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
