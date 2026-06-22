package com.groupcordillera.ms_kpi.controller;

import com.groupcordillera.ms_kpi.model.Indicador;
import com.groupcordillera.ms_kpi.repository.IndicadorRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IndicadorControllerTest {

    @Test
    void listarIndicadores_debeRetornarLista() {
        IndicadorRepository repository = mock(IndicadorRepository.class);
        IndicadorController controller = new IndicadorController(repository);

        Indicador indicador = crearIndicador(1L, "Ventas mensuales", 120.0, 100.0);

        when(repository.findAll()).thenReturn(List.of(indicador));

        List<Indicador> resultado = controller.listarIndicadores();

        assertEquals(1, resultado.size());
        assertEquals("Ventas mensuales", resultado.get(0).getNombre());
        verify(repository).findAll();
    }

    @Test
    void obtenerIndicador_debeRetornarIndicador() {
        IndicadorRepository repository = mock(IndicadorRepository.class);
        IndicadorController controller = new IndicadorController(repository);

        Indicador indicador = crearIndicador(1L, "Cumplimiento", 80.0, 100.0);

        when(repository.findById(1L)).thenReturn(Optional.of(indicador));

        Indicador resultado = controller.obtenerIndicador(1L);

        assertEquals(1L, resultado.getId());
        assertEquals("Cumplimiento", resultado.getNombre());
        verify(repository).findById(1L);
    }

    @Test
    void obtenerIndicador_cuandoNoExiste_debeLanzarRuntimeException() {
        IndicadorRepository repository = mock(IndicadorRepository.class);
        IndicadorController controller = new IndicadorController(repository);

        when(repository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> controller.obtenerIndicador(99L));

        assertEquals("Indicador no encontrado", exception.getMessage());
    }

    @Test
    void crearIndicador_debeGuardarIndicador() {
        IndicadorRepository repository = mock(IndicadorRepository.class);
        IndicadorController controller = new IndicadorController(repository);

        Indicador indicador = crearIndicador(null, "Satisfacción", 90.0, 85.0);
        Indicador guardado = crearIndicador(1L, "Satisfacción", 90.0, 85.0);

        when(repository.save(indicador)).thenReturn(guardado);

        Indicador resultado = controller.crearIndicador(indicador);

        assertNotNull(resultado.getId());
        assertEquals("Satisfacción", resultado.getNombre());
        verify(repository).save(indicador);
    }

    @Test
    void actualizarIndicador_debeActualizarYGuardarIndicador() {
        IndicadorRepository repository = mock(IndicadorRepository.class);
        IndicadorController controller = new IndicadorController(repository);

        Indicador existente = crearIndicador(1L, "Antiguo", 50.0, 100.0);
        Indicador datos = crearIndicador(null, "Nuevo", 110.0, 100.0);
        datos.setDescripcion("Indicador actualizado");
        datos.setUnidadMedida("%");
        datos.setEstado("Activo");
        datos.setArea("Ventas");

        when(repository.findById(1L)).thenReturn(Optional.of(existente));
        when(repository.save(existente)).thenReturn(existente);

        Indicador resultado = controller.actualizarIndicador(1L, datos);

        assertEquals("Nuevo", resultado.getNombre());
        assertEquals("Indicador actualizado", resultado.getDescripcion());
        assertEquals(110.0, resultado.getValorActual());
        assertEquals(100.0, resultado.getMeta());
        assertEquals("%", resultado.getUnidadMedida());
        assertEquals("Activo", resultado.getEstado());
        assertEquals("Ventas", resultado.getArea());
        verify(repository).save(existente);
    }

    @Test
    void eliminarIndicador_debeEliminarPorId() {
        IndicadorRepository repository = mock(IndicadorRepository.class);
        IndicadorController controller = new IndicadorController(repository);

        controller.eliminarIndicador(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void resumenIndicadores_debeCalcularTotalCumplidosYPendientes() {
        IndicadorRepository repository = mock(IndicadorRepository.class);
        IndicadorController controller = new IndicadorController(repository);

        Indicador cumplido = crearIndicador(1L, "Cumplido", 120.0, 100.0);
        Indicador pendiente = crearIndicador(2L, "Pendiente", 60.0, 100.0);
        Indicador sinDatos = crearIndicador(3L, "Sin datos", null, 100.0);

        when(repository.findAll()).thenReturn(List.of(cumplido, pendiente, sinDatos));

        Map<String, Object> resumen = controller.resumenIndicadores();

        assertEquals(3, resumen.get("totalIndicadores"));
        assertEquals(1L, resumen.get("cumplidos"));
        assertEquals(2L, resumen.get("pendientes"));
    }

    private Indicador crearIndicador(Long id, String nombre, Double valorActual, Double meta) {
        Indicador indicador = new Indicador();
        indicador.setId(id);
        indicador.setNombre(nombre);
        indicador.setDescripcion("Descripción");
        indicador.setValorActual(valorActual);
        indicador.setMeta(meta);
        indicador.setUnidadMedida("%");
        indicador.setEstado("Activo");
        indicador.setArea("Ventas");
        return indicador;
    }
}
