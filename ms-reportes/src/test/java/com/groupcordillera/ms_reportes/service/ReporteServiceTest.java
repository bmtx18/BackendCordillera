package com.groupcordillera.ms_reportes.service;

import com.groupcordillera.ms_reportes.model.Reporte;
import com.groupcordillera.ms_reportes.repository.ReporteRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReporteServiceTest {

    @Test
    void listarTodos_debeRetornarLista() {
        ReporteRepository repository = mock(ReporteRepository.class);
        ReporteService service = new ReporteService(repository);

        Reporte reporte = new Reporte();
        reporte.setId(1L);
        reporte.setTipo("USUARIO");

        when(repository.findAll()).thenReturn(List.of(reporte));

        List<Reporte> resultado = service.listarTodos();

        assertEquals(1, resultado.size());
        assertEquals("USUARIO", resultado.get(0).getTipo());
        verify(repository, times(1)).findAll();
    }

    @Test
    void listarPorTipo_debeBuscarTipoEnMayusculas() {
        ReporteRepository repository = mock(ReporteRepository.class);
        ReporteService service = new ReporteService(repository);

        Reporte reporte = new Reporte();
        reporte.setId(1L);
        reporte.setTipo("USUARIO");

        when(repository.findByTipo("USUARIO")).thenReturn(List.of(reporte));

        List<Reporte> resultado = service.listarPorTipo("usuario");

        assertEquals(1, resultado.size());
        verify(repository, times(1)).findByTipo("USUARIO");
    }

    @Test
    void buscarPorId_debeRetornarReporte() {
        ReporteRepository repository = mock(ReporteRepository.class);
        ReporteService service = new ReporteService(repository);

        Reporte reporte = new Reporte();
        reporte.setId(1L);
        reporte.setDescripcion("Producto dañado");

        when(repository.findById(1L)).thenReturn(Optional.of(reporte));

        Reporte resultado = service.buscarPorId(1L);

        assertEquals(1L, resultado.getId());
        assertEquals("Producto dañado", resultado.getDescripcion());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void buscarPorId_cuandoNoExiste_debeLanzarExcepcion() {
        ReporteRepository repository = mock(ReporteRepository.class);
        ReporteService service = new ReporteService(repository);

        when(repository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> service.buscarPorId(99L));

        assertEquals("Reporte no encontrado con id: 99", exception.getMessage());
        verify(repository, times(1)).findById(99L);
    }

    @Test
    void guardarReporte_debeGuardarYRetornarReporte() {
        ReporteRepository repository = mock(ReporteRepository.class);
        ReporteService service = new ReporteService(repository);

        Reporte reporte = new Reporte();
        reporte.setTipo("RECLAMO");
        reporte.setDescripcion("No llegó el pedido");

        Reporte guardado = new Reporte();
        guardado.setId(1L);
        guardado.setTipo("RECLAMO");
        guardado.setDescripcion("No llegó el pedido");

        when(repository.save(reporte)).thenReturn(guardado);

        Reporte resultado = service.guardarReporte(reporte);

        assertEquals(1L, resultado.getId());
        assertEquals("RECLAMO", resultado.getTipo());
        verify(repository, times(1)).save(reporte);
    }

    @Test
    void eliminarReporte_debeEliminarPorId() {
        ReporteRepository repository = mock(ReporteRepository.class);
        ReporteService service = new ReporteService(repository);

        service.eliminarReporte(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}
