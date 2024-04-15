package com.clientes.clientes.service;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UsuarioDTO {
    Long id;
    String rut;
    String rol;
    String nombre;
    String apellido;
    String direccion;

    public UsuarioDTO(Long id, String rut, String rol, String nombre, String apellido, String direccion) {
        this.id = id;
        this.direccion = direccion;
        this.nombre = nombre;
        this.apellido = apellido;
        this.rut = rut;
        this.rol = rol;
    }

    public UsuarioDTO() {

    }

}
