package com.cordillera.ms.usuarios.repository;

import com.cordillera.ms.usuarios.entity.CalificacionDriver;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CalificacionRepositoryTest {

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Test
    void findByDriverId_debeRetornarCalificacionesDelDriver() {
        CalificacionDriver calificacion = CalificacionDriver.builder()
                .driverId(10L)
                .clienteNombre("Cliente Test")
                .clienteCorreo("cliente@test.cl")
                .puntuacion(5)
                .comentario("Excelente")
                .build();

        calificacionRepository.save(calificacion);

        List<CalificacionDriver> resultado = calificacionRepository.findByDriverId(10L);

        assertEquals(1, resultado.size());
        assertEquals(5, resultado.get(0).getPuntuacion());
        assertEquals("Excelente", resultado.get(0).getComentario());
    }
}