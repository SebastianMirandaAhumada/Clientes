package com.clientes.clientes.controller;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.clientes.clientes.model.Usuarios;
import com.clientes.clientes.service.UsuarioServiceImpl;

@WebMvcTest
public class UsuarioControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UsuarioServiceImpl usuarioServiceMock;

    @Test
    public void obtenerTodosUsuarios() throws Exception{
        Usuarios usuario1 = new Usuarios();
        usuario1.setNombre("seba");
        usuario1.setId(1L);
    
        Usuarios usuario2 = new Usuarios();
        usuario2.setNombre("nicolas");
        usuario2.setId(2L);

        List<Usuarios> usuarios = Arrays.asList(usuario1, usuario2);
        when(usuarioServiceMock.getAllUsuarios()).thenReturn(usuarios);

        mockMvc.perform(MockMvcRequestBuilders.get("/usuarios"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.aMapWithSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuariosList[0].nombre", Matchers.is("seba")))
                .andExpect(MockMvcResultMatchers.jsonPath("$._embedded.usuariosList[1].nombre", Matchers.is("nicolas")));
        
    }
}
