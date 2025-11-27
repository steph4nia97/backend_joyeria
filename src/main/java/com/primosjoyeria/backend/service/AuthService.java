package com.primosjoyeria.backend.service;

import com.primosjoyeria.backend.dto.LoginRequest;
import com.primosjoyeria.backend.dto.LoginResponse;
import com.primosjoyeria.backend.dto.RegistroRequest;
import com.primosjoyeria.backend.entity.Usuario;
import com.primosjoyeria.backend.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    public AuthService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public LoginResponse register(RegistroRequest request) {

        if (usuarioRepository.existsByCorreo(request.getCorreo())) {
            throw new RuntimeException("El correo ya está registrado");
        }

        Usuario usuario = new Usuario();
        usuario.setCorreo(request.getCorreo());
        usuario.setPasswordHash(request.getPassword()); // SIN HASH
        usuario.setSexo(request.getSexo());
        usuario.setEdad(request.getEdad());
        usuario.setRol("CLIENTE");

        Usuario guardado = usuarioRepository.save(usuario);

        return new LoginResponse(
                guardado.getId(),
                guardado.getCorreo(),
                guardado.getRol()
        );
    }

    public LoginResponse login(LoginRequest request) {
        Usuario usuario = usuarioRepository.findByCorreo(request.getCorreo())
                .orElseThrow(() -> new RuntimeException("Usuario o contraseña incorrecta"));

        if (!request.getPassword().equals(usuario.getPasswordHash())) {
            throw new RuntimeException("Usuario o contraseña incorrecta");
        }

        return new LoginResponse(
                usuario.getId(),
                usuario.getCorreo(),
                usuario.getRol()
        );
    }
}
