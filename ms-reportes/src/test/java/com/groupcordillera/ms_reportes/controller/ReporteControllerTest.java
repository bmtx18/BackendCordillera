package com.groupcordillera.ms_reportes.controller;

import com.groupcordillera.ms_reportes.model.Reporte;
import com.groupcordillera.ms_reportes.repository.ReporteRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReporteControllerTest {

    @Test
    void listarReportes_debeRetornarLista() {
        ReporteRepository repository = mock(ReporteRepository.class);
        ReporteController controller = new ReporteController(repository);

        Reporte reporte = new Reporte();
        reporte.setId(1L);
        reporte.setClienteNombre("Benjamin");

        when(repository.findAll()).thenReturn(List.of(reporte));

        List<Reporte> resultado = controller.listarReportes();

        assertEquals(1, resultado.size());
        assertEquals("Benjamin", resultado.get(0).getClienteNombre());
        verify(repository, times(1)).findAll();
    }

    @Test
    void buscarPorId_debeRetornarReporte() {
        ReporteRepository repository = mock(ReporteRepository.class);
        ReporteController controller = new ReporteController(repository);

        Reporte reporte = new Reporte();
        reporte.setId(1L);
        reporte.setTipo("USUARIO");

        when(repository.findById(1L)).thenReturn(Optional.of(reporte));

        Reporte resultado = controller.buscarPorId(1L);

        assertEquals(1L, resultado.getId());
        assertEquals("USUARIO", resultado.getTipo());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void buscarPorId_cuandoNoExiste_debeLanzarExcepcion() {
        ReporteRepository repository = mock(ReporteRepository.class);
        ReporteController controller = new ReporteController(repository);

        when(repository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> controller.buscarPorId(99L));

        assertEquals("Reporte no encontrado", exception.getMessage());
    }

    @Test
    void listarReportesUsuarios_debeRetornarLista() {
        ReporteRepository repository = mock(ReporteRepository.class);
        ReporteController controller = new ReporteController(repository);

        Reporte reporte = new Reporte();
        reporte.setTipo("USUARIO");

        when(repository.findAll()).thenReturn(List.of(reporte));

        List<Reporte> resultado = controller.listarReportesUsuarios();

        assertEquals(1, resultado.size());
        assertEquals("USUARIO", resultado.get(0).getTipo());
        verify(repository, times(1)).findAll();
    }

    @Test
    void crearReporte_debeAsignarValoresInicialesYGuardar() {
        ReporteRepository repository = mock(ReporteRepository.class);
        ReporteController controller = new ReporteController(repository);

        Reporte reporte = new Reporte();
        reporte.setClienteNombre("Benjamin");
        reporte.setClienteCorreo("benjamin@test.com");
        reporte.setTipo("RECLAMO");
        reporte.setDescripcion("Producto defectuoso");

        when(repository.save(any(Reporte.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Reporte resultado = controller.crearReporte(reporte);

        assertEquals("Pendiente", resultado.getEstado());
        assertEquals("", resultado.getRespuestaAdmin());
        assertNotNull(resultado.getFechaCreacion());
        assertEquals("", resultado.getFechaRespuesta());
        verify(repository, times(1)).save(reporte);
    }

    @Test
    void crearReporteUsuario_debeAsignarValoresInicialesYGuardar() {
        ReporteRepository repository = mock(ReporteRepository.class);
        ReporteController controller = new ReporteController(repository);

        Reporte reporte = new Reporte();
        reporte.setClienteNombre("Usuario Test");
        reporte.setTipo("USUARIO");

        when(repository.save(any(Reporte.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Reporte resultado = controller.crearReporteUsuario(reporte);

        assertEquals("Pendiente", resultado.getEstado());
        assertEquals("", resultado.getRespuestaAdmin());
        assertNotNull(resultado.getFechaCreacion());
        assertEquals("", resultado.getFechaRespuesta());
        verify(repository, times(1)).save(reporte);
    }

    @Test
    void responderReporte_debeActualizarRespuestaEstadoYFecha() {
        ReporteRepository repository = mock(ReporteRepository.class);
        ReporteController controller = new ReporteController(repository);

        Reporte reporte = new Reporte();
        reporte.setId(1L);
        reporte.setEstado("Pendiente");

        when(repository.findById(1L)).thenReturn(Optional.of(reporte));
        when(repository.save(any(Reporte.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Reporte resultado = controller.responderReporte(1L, Map.of("respuestaAdmin", "Caso solucionado"));

        assertEquals("Caso solucionado", resultado.getRespuestaAdmin());
        assertEquals("Respondido", resultado.getEstado());
        assertNotNull(resultado.getFechaRespuesta());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(reporte);
    }

    @Test
    void responderReporte_cuandoNoExiste_debeLanzarExcepcion() {
        ReporteRepository repository = mock(ReporteRepository.class);
        ReporteController controller = new ReporteController(repository);

        when(repository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> controller.responderReporte(99L, Map.of("respuestaAdmin", "Respuesta"))
        );

        assertEquals("Reporte no encontrado", exception.getMessage());
        verify(repository, never()).save(any(Reporte.class));
    }

    @Test
    void eliminarReporte_debeEliminarPorId() {
        ReporteRepository repository = mock(ReporteRepository.class);
        ReporteController controller = new ReporteController(repository);

        controller.eliminarReporte(1L);

        verify(repository, times(1)).deleteById(1L);
    }
}
