package com.cordillera.ms.usuarios.repository;

import com.cordillera.ms.usuarios.entity.Antecedente;
import com.cordillera.ms.usuarios.entity.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AntecedenteRepositoryTest {

    @Autowired
    private AntecedenteRepository antecedenteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void guardarAntecedente_debePersistirCorrectamente() {
        Usuario usuario = Usuario.builder()
                .nombre("Benjamin")
                .correo("benjamin@test.cl")
                .password("1234")
                .rol("CLIENTE")
                .activo(true)
                .build();

        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        Antecedente antecedente = Antecedente.builder()
                .comprasRealizadas(3)
                .reportesRealizados(1)
                .aniosActivo(2)
                .usuario(usuarioGuardado)
                .build();

        Antecedente resultado = antecedenteRepository.save(antecedente);

        assertNotNull(resultado.getId());
        assertEquals(3, resultado.getComprasRealizadas());
        assertEquals(usuarioGuardado.getId(), resultado.getUsuario().getId());
    }
}