package com.clientes.clientes.service;

import java.util.List;
import java.util.Optional;

import com.clientes.clientes.model.Usuarios;

public interface UsuarioService {
    List<Usuarios> getAllUsuarios();

    Optional<Usuarios> getUsuarioById(Long id);

    Usuarios createUsuarios(Usuarios usuario);

    Usuarios updateUsuarios(Long id, Usuarios usuario);

    void deleteUsuarios(Long id);

    Usuarios login(String usuarioLogin, String password);
}
