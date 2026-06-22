package com.groupcordillera.ms_kpi.repository;

import com.groupcordillera.ms_kpi.model.Indicador;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IndicadorRepositoryTest {

    @Autowired
    private IndicadorRepository indicadorRepository;

    @Test
    void guardarIndicador_debePersistirCorrectamente() {
        Indicador indicador = crearIndicador("Ventas mensuales", 120.0, 100.0);

        Indicador resultado = indicadorRepository.save(indicador);

        assertNotNull(resultado.getId());
        assertEquals("Ventas mensuales", resultado.getNombre());
        assertEquals(120.0, resultado.getValorActual());
    }

    @Test
    void findById_debeRetornarIndicador() {
        Indicador indicador = indicadorRepository.save(crearIndicador("Cumplimiento", 80.0, 100.0));

        Optional<Indicador> resultado = indicadorRepository.findById(indicador.getId());

        assertTrue(resultado.isPresent());
        assertEquals("Cumplimiento", resultado.get().getNombre());
    }

    @Test
    void findAll_debeRetornarIndicadores() {
        indicadorRepository.save(crearIndicador("KPI 1", 50.0, 100.0));
        indicadorRepository.save(crearIndicador("KPI 2", 150.0, 100.0));

        List<Indicador> resultado = indicadorRepository.findAll();

        assertEquals(2, resultado.size());
    }

    private Indicador crearIndicador(String nombre, Double valorActual, Double meta) {
        Indicador indicador = new Indicador();
        indicador.setNombre(nombre);
        indicador.setDescripcion("Descripción de prueba");
        indicador.setValorActual(valorActual);
        indicador.setMeta(meta);
        indicador.setUnidadMedida("%");
        indicador.setEstado("Activo");
        indicador.setArea("Ventas");
        return indicador;
    }
}
