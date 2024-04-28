package com.clientes.clientes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.clientes.clientes.model.Usuarios;
import com.clientes.clientes.service.UsuarioService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private List<Usuarios> usuario = new ArrayList<>();
    private static final Logger log = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public CollectionModel<EntityModel<Usuarios>> getAllUsuarios() {
        List<Usuarios> usuarios = usuarioService.getAllUsuarios();
        List<EntityModel<Usuarios>> usuarioResource = usuarios.stream()
                .map(usuario -> EntityModel.of(usuario,
                        WebMvcLinkBuilder
                                .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioById(usuario.getId()))
                                .withSelfRel()))
                .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(getClass()).getAllUsuarios());
        CollectionModel<EntityModel<Usuarios>> resource = CollectionModel.of(usuarioResource,
                linkTo.withRel("usuarios"));
        return resource;

    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Long id) {
        for (Usuarios usuario : usuario) {
            if (usuario.getId() == id) {
                return ResponseEntity.ok(usuario);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("No hay usuarios registrados con ese id: " + id);
    }

    @PostMapping
    public ResponseEntity<String> createUsuarios(@RequestBody Usuarios usuarios) {
        Usuarios createdUsuario = usuarioService.createUsuarios(usuarios);
        if (createdUsuario != null) {
            String mensaje = "Usuario creado con éxito";
            return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
        } else {
            String mensaje = "No se pudo crear el usuario";
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje);
        }
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<String> updateUsuarios(@PathVariable Long id, @RequestBody Usuarios usuarios) {
        Usuarios updatedUsuario = usuarioService.updateUsuarios(id, usuarios);
        if (updatedUsuario != null) {
            String mensaje = "Usuario actualizado con éxito";
            return ResponseEntity.ok(mensaje);
        } else {
            String mensaje = "No se encontró el usuario con ID " + id + " o no se pudo actualizar";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUsuarios(@PathVariable Long id) {
        usuarioService.deleteUsuarios(id);
        String mensaje = "Usuario eliminado exitosamente";
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(mensaje);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuarios usuarios) {
        Usuarios usuarioEncontrado = usuarioService.login(usuarios.getUsuarioLogin(), usuarios.getPassword());
        if (usuarioEncontrado != null) {
            return ResponseEntity.ok(usuarioEncontrado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario o contraseña incorrectos");
        }
    }

}