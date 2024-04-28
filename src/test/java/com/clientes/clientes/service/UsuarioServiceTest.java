package com.clientes.clientes.service;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import com.clientes.clientes.model.Usuarios;
import com.clientes.clientes.repository.UsuarioRepository;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    
    @InjectMocks
    private UsuarioServiceImpl usuarioServiceImpl;

    @Mock
    private UsuarioRepository usuarioRepositoryMock;

    @Test
    public void guardarClienteTest(){
        Usuarios usuarios = new Usuarios();
        usuarios.setNombre("sebastian");

        when(usuarioRepositoryMock.save(any())).thenReturn(usuarios);

        Usuarios resultado = usuarioServiceImpl.createUsuarios(usuarios);
        assertEquals("sebastian", resultado.getNombre());

    }


}
