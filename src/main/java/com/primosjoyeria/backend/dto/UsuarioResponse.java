package com.primosjoyeria.backend.dto;

import lombok.Data;

@Data
public class UsuarioResponse {
    private Long id;
    private String correo;
    private String rol;
}
