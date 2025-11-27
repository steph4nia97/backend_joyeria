package com.primosjoyeria.backend.controller;

import com.primosjoyeria.backend.dto.LoginRequest;
import com.primosjoyeria.backend.dto.RegistroRequest;
import com.primosjoyeria.backend.dto.LoginResponse;
import com.primosjoyeria.backend.entity.Usuario;
import com.primosjoyeria.backend.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioRepository usuarioRepo;

    public AuthController(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    // ========= REGISTRO =========
    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody RegistroRequest request) {

        String correo = request.getCorreo().trim();

        // 1) Validar que no exista el correo
        if (usuarioRepo.existsByCorreo(correo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo ya está registrado");
        }

        // 2) Crear entidad Usuario
        Usuario u = new Usuario();
        u.setCorreo(correo);
        u.setPasswordHash(request.getPassword()); // plano, sin encriptar de momento
        u.setSexo(request.getSexo().trim().toUpperCase());
        u.setEdad(request.getEdad());
        u.setRol("CLIENTE");

        // 3) Guardar
        Usuario saved = usuarioRepo.save(u);

        // 4) Devolver respuesta simple para la app
        LoginResponse resp = new LoginResponse(
                saved.getId(),
                saved.getCorreo(),
                saved.getRol()
        );

        return ResponseEntity.ok(resp);
    }

    // ========= LOGIN =========
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {

        String correo = request.getCorreo().trim();

        Usuario u = usuarioRepo.findByCorreo(correo)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas")
                );

        // Estamos usando password plano (sin Bcrypt)
        if (!u.getPasswordHash().equals(request.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Credenciales inválidas");
        }

        LoginResponse resp = new LoginResponse(
                u.getId(),
                u.getCorreo(),
                u.getRol()
        );

        return ResponseEntity.ok(resp);
    }
}