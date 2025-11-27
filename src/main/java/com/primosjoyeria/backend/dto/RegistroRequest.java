package com.primosjoyeria.backend.dto;


import lombok.Data;

@Data
    public class RegistroRequest {
    private String correo;
    private String password;
    private String sexo;  // "F", "M", "X"
    private Integer edad;


    public RegistroRequest(){}

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public Integer getEdad() { return edad; }
    public void setEdad(Integer edad) { this.edad = edad; }
    }


