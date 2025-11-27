package com.primosjoyeria.backend.controller;

import com.primosjoyeria.backend.entity.Usuario;
import com.primosjoyeria.backend.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    // GET /api/usuarios - listar todos
    @GetMapping
    public List<Usuario> listar() {
        return service.listar();
    }

    // GET /api/usuarios/{id} - obtener uno
    @GetMapping("/{id}")
    public Usuario obtener(@PathVariable Long id) {
        return service.buscar(id);
    }

    // POST /api/usuarios - crear
    @PostMapping
    public Usuario crear(@RequestBody Usuario u) {
        return service.crear(u);
    }

    // PUT /api/usuarios/{id} - actualizar
    @PutMapping("/{id}")
    public Usuario actualizar(@PathVariable Long id, @RequestBody Usuario u) {
        return service.actualizar(id, u);
    }

    // DELETE /api/usuarios/{id} - eliminar
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
