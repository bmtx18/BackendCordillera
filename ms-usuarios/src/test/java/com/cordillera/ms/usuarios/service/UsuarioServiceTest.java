package com.cordillera.ms.usuarios.service;

import com.cordillera.ms.usuarios.dto.LoginRequest;
import com.cordillera.ms.usuarios.dto.RegistroRequest;
import com.cordillera.ms.usuarios.entity.Usuario;
import com.cordillera.ms.usuarios.repository.AntecedenteRepository;
import com.cordillera.ms.usuarios.repository.CalificacionRepository;
import com.cordillera.ms.usuarios.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private AntecedenteRepository antecedenteRepository;

    @Mock
    private CalificacionRepository calificacionRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrar_debeGuardarUsuario() {

        RegistroRequest request = new RegistroRequest();
        request.setNombre("Benjamin");
        request.setCorreo("benjamin@test.cl");
        request.setPassword("1234");
        request.setRol("CLIENTE");

        Usuario usuario = Usuario.builder()
                .id(1L)
                .nombre(request.getNombre())
                .correo(request.getCorreo())
                .password(request.getPassword())
                .rol(request.getRol())
                .activo(true)
                .build();

        when(usuarioRepository.existsByCorreo(request.getCorreo()))
                .thenReturn(false);

        when(usuarioRepository.save(any(Usuario.class)))
                .thenReturn(usuario);

        Usuario resultado = usuarioService.registrar(request);

        assertNotNull(resultado);
        assertEquals("Benjamin", resultado.getNombre());

        verify(usuarioRepository).save(any(Usuario.class));
        verify(antecedenteRepository).save(any());
    }

    @Test
    void login_debeRetornarUsuario() {

        LoginRequest request = new LoginRequest();
        request.setCorreo("test@test.cl");
        request.setPassword("1234");

        Usuario usuario = Usuario.builder()
                .id(1L)
                .correo("test@test.cl")
                .password("1234")
                .activo(true)
                .build();

        when(usuarioRepository.findByCorreo("test@test.cl"))
                .thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.login(request);

        assertNotNull(resultado);
        assertEquals("test@test.cl", resultado.getCorreo());
    }

    @Test
    void buscarPorId_debeRetornarUsuario() {

        Usuario usuario = Usuario.builder()
                .id(1L)
                .nombre("Benjamin")
                .build();

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        Usuario resultado = usuarioService.buscarPorId(1L);

        assertEquals("Benjamin", resultado.getNombre());
    }

    @Test
    void cambiarEstado_debeActualizarUsuario() {

        Usuario usuario = Usuario.builder()
                .id(1L)
                .activo(true)
                .build();

        when(usuarioRepository.findById(1L))
                .thenReturn(Optional.of(usuario));

        when(usuarioRepository.save(any(Usuario.class)))
                .thenReturn(usuario);

        Usuario resultado = usuarioService.cambiarEstado(1L,false);

        assertFalse(resultado.getActivo());

        verify(usuarioRepository).save(usuario);
    }
}