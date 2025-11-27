package com.primosjoyeria.backend.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Long id;



    @Column(name = "CORREO", unique = true, nullable = false)
    private String correo;

    @Column(name = "PASSWORD_HASH", nullable = false)
    private String passwordHash;

    @Column(name = "SEXO", nullable = false)
    private String sexo; // F, M, X

    @Column(name = "EDAD", nullable = false)
    private Integer edad;

    @Column(name = "ROL", nullable = false)
    private String rol = "CLIENTE"; // CLIENTE o ADMIN

    // ---- Constructores ----
    public Usuario() {
    }

    public Usuario(String correo, String passwordHash, String sexo, Integer edad, String rol) {
        this.correo = correo;
        this.passwordHash = passwordHash;
        this.sexo = sexo;
        this.edad = edad;
        this.rol = rol;
    }

    // ---- Getters y Setters ----
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }


    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}