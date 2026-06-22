package com.cordillera.ms.usuarios.repository;

import com.cordillera.ms.usuarios.entity.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void findByCorreo_debeRetornarUsuario() {
        Usuario usuario = Usuario.builder()
                .nombre("Benjamin")
                .correo("benjamin@test.cl")
                .password("1234")
                .rol("CLIENTE")
                .activo(true)
                .build();

        usuarioRepository.save(usuario);

        Optional<Usuario> resultado = usuarioRepository.findByCorreo("benjamin@test.cl");

        assertTrue(resultado.isPresent());
        assertEquals("Benjamin", resultado.get().getNombre());
    }

    @Test
    void existsByCorreo_debeRetornarTrue() {
        Usuario usuario = Usuario.builder()
                .nombre("Benjamin")
                .correo("benjamin@test.cl")
                .password("1234")
                .rol("CLIENTE")
                .activo(true)
                .build();

        usuarioRepository.save(usuario);

        assertTrue(usuarioRepository.existsByCorreo("benjamin@test.cl"));
    }

    @Test
    void findByRol_debeRetornarUsuariosPorRol() {
        Usuario driver = Usuario.builder()
                .nombre("Driver Uno")
                .correo("driver@test.cl")
                .password("1234")
                .rol("DRIVER")
                .activo(true)
                .build();

        usuarioRepository.save(driver);

        List<Usuario> resultado = usuarioRepository.findByRol("DRIVER");

        assertEquals(1, resultado.size());
        assertEquals("Driver Uno", resultado.get(0).getNombre());
    }
}