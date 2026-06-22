package com.groupcordillera.bff.controller;

import com.groupcordillera.bff.service.ReporteClient;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReporteControllerTest {

    @Test
    void listarReportes_debeRetornarReportes() {
        ReporteClient reporteClient = mock(ReporteClient.class);
        ReporteController controller = new ReporteController(reporteClient);
        Object reportes = List.of(Map.of("id", 1L));

        when(reporteClient.listarReportes()).thenReturn(reportes);

        Object resultado = controller.listarReportes();

        assertEquals(reportes, resultado);
        verify(reporteClient).listarReportes();
    }

    @Test
    void crearReporteUsuarios_debeDelegarEnReporteClient() {
        ReporteClient reporteClient = mock(ReporteClient.class);
        ReporteController controller = new ReporteController(reporteClient);
        Object request = Map.of("titulo", "Problema usuario");
        Object creado = Map.of("id", 1L);

        when(reporteClient.crearReporteUsuarios(request)).thenReturn(creado);

        Object resultado = controller.crearReporteUsuarios(request);

        assertEquals(creado, resultado);
        verify(reporteClient).crearReporteUsuarios(request);
    }

    @Test
    void buscarPorId_debeDelegarEnReporteClient() {
        ReporteClient reporteClient = mock(ReporteClient.class);
        ReporteController controller = new ReporteController(reporteClient);
        Object reporte = Map.of("id", 1L);

        when(reporteClient.buscarPorId(1L)).thenReturn(reporte);

        Object resultado = controller.buscarPorId(1L);

        assertEquals(reporte, resultado);
        verify(reporteClient).buscarPorId(1L);
    }
}
