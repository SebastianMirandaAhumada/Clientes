package com.clientes.clientes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.clientes.clientes.model.Usuarios;

@RestController
public class UsuarioController {
    private List<Usuarios> usuario = new ArrayList<>();

    public UsuarioController(){
        usuario.add(new Usuarios(1, "1536823-3", "Sebastian", "Miranda", "Admin", "Av.Siempre Viva 123"));
        usuario.add(new Usuarios(2, "4816660-1", "Antonio", "Banderas", "clientedePago", "Av.Siempre Viva 555"));
        usuario.add(new Usuarios(3, "1319648-6", "Gabriela", "Medina", "clientedePago", "Av.Siempre Viva 777"));
        usuario.add(new Usuarios(4, "2019571-1", "Homero", "Simpson", "clienteNormal", "Av.Siempre Viva 888"));
        usuario.add(new Usuarios(5, "1551077-3", "Alexis", "Sanchez", "clienteNormal", "Av.Siempre Viva 971"));
        usuario.add(new Usuarios(6, "2059862-k", "Bart", "Simpson", "clienteNormal", "Av.Siempre Viva 092"));
    }

    @GetMapping("/usuarios")
    public List<Usuarios> getUsuarios() {
        return usuario;
    }
    @GetMapping("/usuarios/Id/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable int id) {
        for (Usuarios usuario : usuario) {
            if (usuario.getId() == id) {
                return ResponseEntity.ok(usuario);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("No hay atenciones registradas con ese id: " + id);
    }

    @GetMapping("/usuarios/Rut/{rut}")
    public ResponseEntity<?> getUsuarioByRut(@PathVariable String rut) {
        for (Usuarios usuario : usuario) {
            if (usuario.getRut().equals(rut)) {
                return ResponseEntity.ok(usuario);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("No hay atenciones registradas con ese RUT: " + rut);
    }

    @GetMapping("/usuarios/Rol/{rol}")
    public  ResponseEntity<?>  getUsuarioByRol(@PathVariable String rol) {
        for (Usuarios usuario : usuario) {
            if (usuario.getRol().equals(rol)) {
                return ResponseEntity.ok(usuario);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body("No hay atenciones registradas con ese rol: " + rol);
    }
}