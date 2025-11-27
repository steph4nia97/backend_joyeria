package com.primosjoyeria.backend.service;

import com.primosjoyeria.backend.entity.Usuario;
import com.primosjoyeria.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    public List<Usuario> listar() {
        return repo.findAll();
    }

    public Usuario buscar(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public Usuario buscarPorCorreo(String correo) {
        return repo.findByCorreo(correo).orElse(null);
    }

    public Usuario crear(Usuario u) {
        // Si no viene rol desde el front, seteamos CLIENTE
        if (u.getRol() == null || u.getRol().isBlank()) {
            u.setRol("CLIENTE");
        }
        return repo.save(u);
    }

    public Usuario actualizar(Long id, Usuario nuevo) {
        Usuario actual = buscar(id);
        actual.setCorreo(nuevo.getCorreo());
        actual.setRol(nuevo.getRol() != null ? nuevo.getRol() : actual.getRol());
        // Por ahora permitimos cambiar passwordHash tal cual, más adelante se hará con hash
        if (nuevo.getPasswordHash() != null && !nuevo.getPasswordHash().isBlank()) {
            actual.setPasswordHash(nuevo.getPasswordHash());
        }
        return repo.save(actual);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
