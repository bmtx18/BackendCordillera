package com.groupcordillera.ms_kpi.controller;

import com.groupcordillera.ms_kpi.model.Area;
import com.groupcordillera.ms_kpi.repository.AreaRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AreaControllerTest {

    @Test
    void listarAreas_debeRetornarLista() {
        AreaRepository repository = mock(AreaRepository.class);
        AreaController controller = new AreaController(repository);

        Area area = Area.builder()
                .id(1L)
                .nombre("Ventas")
                .build();

        when(repository.findAll()).thenReturn(List.of(area));

        List<Area> resultado = controller.listarAreas();

        assertEquals(1, resultado.size());
        assertEquals("Ventas", resultado.get(0).getNombre());
        verify(repository).findAll();
    }

    @Test
    void crearArea_debeGuardarArea() {
        AreaRepository repository = mock(AreaRepository.class);
        AreaController controller = new AreaController(repository);

        Area area = Area.builder()
                .nombre("Logística")
                .build();

        Area areaGuardada = Area.builder()
                .id(1L)
                .nombre("Logística")
                .build();

        when(repository.save(area)).thenReturn(areaGuardada);

        Area resultado = controller.crearArea(area);

        assertNotNull(resultado.getId());
        assertEquals("Logística", resultado.getNombre());
        verify(repository).save(area);
    }
}
