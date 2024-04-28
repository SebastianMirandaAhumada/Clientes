package com.clientes.clientes.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.clientes.clientes.model.Usuarios;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void guardarApellidoUsuario() {
        Usuarios usuario = new Usuarios();
        usuario.setApellido("miranda");
        Usuarios usuarios = usuarioRepository.save(usuario);
        assertNotNull(usuario.getApellido());
        assertEquals("miranda", usuarios.getApellido());
    }
}
