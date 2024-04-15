package com.clientes.clientes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clientes.clientes.model.Usuarios;
import com.clientes.clientes.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuarios> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuarios> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuarios createUsuarios(Usuarios usuarios) {
        return usuarioRepository.save(usuarios);
    }

    @Override
    public Usuarios updateUsuarios(Long id, Usuarios usuarios) {
        if (usuarioRepository.existsById(id)) {
            usuarios.setId(id);
            return usuarioRepository.save(usuarios);

        } else {
            return null;
        }
    }

    @Override
    public void deleteUsuarios(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Usuarios login(String usuarioLogin, String password) {
        return usuarioRepository.findByUsuarioLoginAndPassword(usuarioLogin, password);
    }

}
