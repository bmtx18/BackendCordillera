package com.groupcordillera.bff.controller;

import com.groupcordillera.bff.service.UsuarioClient;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioControllerTest {

    @Test
    void listarUsuarios_debeRetornarUsuarios() {
        UsuarioClient usuarioClient = mock(UsuarioClient.class);
        UsuarioController controller = new UsuarioController(usuarioClient);
        Object usuarios = List.of(Map.of("nombre", "Benjamin"));

        when(usuarioClient.listarUsuarios()).thenReturn(usuarios);

        Object resultado = controller.listarUsuarios();

        assertEquals(usuarios, resultado);
        verify(usuarioClient).listarUsuarios();
    }

    @Test
    void registrar_debeDelegarEnUsuarioClient() {
        UsuarioClient usuarioClient = mock(UsuarioClient.class);
        UsuarioController controller = new UsuarioController(usuarioClient);
        Object request = Map.of("correo", "test@test.cl");
        Object creado = Map.of("id", 1L, "correo", "test@test.cl");

        when(usuarioClient.registrar(request)).thenReturn(creado);

        Object resultado = controller.registrar(request);

        assertEquals(creado, resultado);
        verify(usuarioClient).registrar(request);
    }

    @Test
    void login_debeDelegarEnUsuarioClient() {
        UsuarioClient usuarioClient = mock(UsuarioClient.class);
        UsuarioController controller = new UsuarioController(usuarioClient);
        Object request = Map.of("correo", "test@test.cl", "password", "1234");
        Object login = Map.of("correo", "test@test.cl");

        when(usuarioClient.login(request)).thenReturn(login);

        Object resultado = controller.login(request);

        assertEquals(login, resultado);
        verify(usuarioClient).login(request);
    }

    @Test
    void eliminar_debeRetornarMensajeCorrecto() {
        UsuarioClient usuarioClient = mock(UsuarioClient.class);
        UsuarioController controller = new UsuarioController(usuarioClient);

        String resultado = controller.eliminar(1L);

        assertEquals("Usuario eliminado correctamente", resultado);
        verify(usuarioClient).eliminar(1L);
    }
}
