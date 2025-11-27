package com.primosjoyeria.backend.dto;

public class LoginResponse {

    private Long id;
    private String correo;
    private String rol;

    public LoginResponse() {}

    public LoginResponse(Long id, String correo, String rol) {
        this.id = id;
        this.correo = correo;
        this.rol = rol;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}
