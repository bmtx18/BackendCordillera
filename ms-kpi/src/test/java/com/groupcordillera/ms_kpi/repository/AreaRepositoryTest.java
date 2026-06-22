package com.groupcordillera.ms_kpi.repository;

import com.groupcordillera.ms_kpi.model.Area;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AreaRepositoryTest {

    @Autowired
    private AreaRepository areaRepository;

    @Test
    void guardarArea_debePersistirCorrectamente() {
        Area area = Area.builder()
                .nombre("Ventas")
                .build();

        Area resultado = areaRepository.save(area);

        assertNotNull(resultado.getId());
        assertEquals("Ventas", resultado.getNombre());
    }

    @Test
    void findAll_debeRetornarAreas() {
        areaRepository.save(Area.builder().nombre("Ventas").build());
        areaRepository.save(Area.builder().nombre("Logística").build());

        List<Area> resultado = areaRepository.findAll();

        assertEquals(2, resultado.size());
    }
}
