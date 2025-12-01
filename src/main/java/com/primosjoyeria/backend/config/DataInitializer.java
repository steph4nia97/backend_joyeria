package com.primosjoyeria.backend.config;

import com.primosjoyeria.backend.entity.Usuario;
import com.primosjoyeria.backend.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initAdminUser(UsuarioRepository usuarioRepository) {
        return args -> {
            String adminCorreo = "admin@joyeria.cl";

            if (!usuarioRepository.existsByCorreo(adminCorreo)) {
                Usuario admin = new Usuario();
                admin.setCorreo(adminCorreo);
                admin.setPasswordHash("admin123"); // SIN HASH
                admin.setSexo("X");
                admin.setEdad(30);
                admin.setRol("ADMIN");

                usuarioRepository.save(admin);
                System.out.println("Usuario ADMIN creado: " + adminCorreo + " / admin123");
            }
        };
    }
}