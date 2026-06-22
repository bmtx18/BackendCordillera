package com.cordillera.ms.usuarios.controller;

import com.cordillera.ms.usuarios.entity.Usuario;
import com.cordillera.ms.usuarios.service.UsuarioService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioControllerTest {

    @Test
    void listar_debeRetornarUsuarios() {

        UsuarioService service = mock(UsuarioService.class);

        UsuarioController controller =
                new UsuarioController(service);

        Usuario usuario = Usuario.builder()
                .id(1L)
                .nombre("Benjamin")
                .build();

        when(service.listarUsuarios())
                .thenReturn(List.of(usuario));

        List<Usuario> resultado = controller.listar();

        assertEquals(1, resultado.size());
        assertEquals("Benjamin", resultado.get(0).getNombre());
    }

    @Test
    void buscarPorId_debeRetornarUsuario() {

        UsuarioService service = mock(UsuarioService.class);

        UsuarioController controller =
                new UsuarioController(service);

        Usuario usuario = Usuario.builder()
                .id(1L)
                .nombre("Benjamin")
                .build();

        when(service.buscarPorId(1L))
                .thenReturn(usuario);

        Usuario resultado = controller.buscarPorId(1L);

        assertEquals("Benjamin", resultado.getNombre());
    }
}