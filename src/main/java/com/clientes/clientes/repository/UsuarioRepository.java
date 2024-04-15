package com.clientes.clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clientes.clientes.model.Usuarios;
import com.clientes.clientes.service.UsuarioDTO;

public interface UsuarioRepository extends JpaRepository<Usuarios, Long> {

    public Usuarios findByUsuarioLoginAndPassword(String usuarioLogin, String password);
}
