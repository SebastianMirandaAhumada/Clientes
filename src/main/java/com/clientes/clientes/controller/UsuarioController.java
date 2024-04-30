package com.clientes.clientes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
                linkTo.withRel("USUARIOS ENCONTRADOS"));
        return resource;

    }

    @GetMapping("/{id}")
    public EntityModel<Usuarios> getUsuarioById(@PathVariable Long id) {
        Optional<Usuarios> usuarioOptional = usuarioService.getUsuarioById(id);
        if (usuarioOptional.isPresent()) {
            return EntityModel.of(usuarioOptional.get(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioById(id))
                            .withSelfRel(),
                    WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios())
                            .withRel("Todos los estudiantes"));

        } else {
            throw new UsuarioNotFoundException("Usuario no puede ser encontrado con el id:" + id);
        }
    }

    @PostMapping
    public EntityModel<Usuarios> createUsuario(@RequestBody Usuarios usuarios) {
        Usuarios crearUsuario = usuarioService.createUsuarios(usuarios);
        return EntityModel.of(crearUsuario,
                WebMvcLinkBuilder
                        .linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioById(crearUsuario.getId()))
                        .withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios())
                        .withRel("Todos los creados usuarios"));
    }

    @PutMapping("/{id}")
    public EntityModel<Usuarios> updateUsuario(@PathVariable Long id, @RequestBody Usuarios usuarios) {
        Usuarios updateUsuarios = usuarioService.updateUsuarios(id, usuarios);
        return EntityModel.of(updateUsuarios,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getUsuarioById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios())
                        .withRel("Todos los Usuarios"));
    }

    @DeleteMapping("/{id}")
    public void deleteUsuarios(@PathVariable Long id) {
        usuarioService.deleteUsuarios(id);
    }

    @PostMapping("/login")
    public  EntityModel<Usuarios> login( @RequestBody Usuarios usuarios) {
        Usuarios usuarioEncontrado = usuarioService.login(usuarios.getUsuarioLogin(), usuarios.getPassword());
        if (usuarioEncontrado != null) {
             EntityModel<Usuarios> usuarioModel = EntityModel.of(usuarioEncontrado,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllUsuarios()).withRel("Usuario Logeado"));
            // usuarioModel.add(linkTo(WebMvcLinkBuilder.methodOn(UsuarioController.class).login(usuarioEncontrado)).withSelfRel()); // Agrega un enlace de "self"
            return usuarioModel;
        } else {
            throw new UsuarioNotFoundException("Usuario no puede logear");
        }
    }

}