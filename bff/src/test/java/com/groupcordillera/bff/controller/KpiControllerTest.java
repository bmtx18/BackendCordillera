package com.groupcordillera.bff.controller;

import com.groupcordillera.bff.service.KpiClient;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class KpiControllerTest {

    @Test
    void listarIndicadores_debeRetornarIndicadores() {
        KpiClient kpiClient = mock(KpiClient.class);
        KpiController controller = new KpiController(kpiClient);
        Object indicadores = List.of(Map.of("nombre", "Ventas mensuales"));

        when(kpiClient.listarIndicadores()).thenReturn(indicadores);

        Object resultado = controller.listarIndicadores();

        assertEquals(indicadores, resultado);
        verify(kpiClient).listarIndicadores();
    }

    @Test
    void crearIndicador_debeDelegarEnKpiClient() {
        KpiClient kpiClient = mock(KpiClient.class);
        KpiController controller = new KpiController(kpiClient);
        Object request = Map.of("nombre", "Ventas mensuales");
        Object creado = Map.of("id", 1L);

        when(kpiClient.crearIndicador(request)).thenReturn(creado);

        Object resultado = controller.crearIndicador(request);

        assertEquals(creado, resultado);
        verify(kpiClient).crearIndicador(request);
    }

    @Test
    void actualizarIndicador_debeDelegarEnKpiClient() {
        KpiClient kpiClient = mock(KpiClient.class);
        KpiController controller = new KpiController(kpiClient);
        Object request = Map.of("nombre", "KPI actualizado");
        Object actualizado = Map.of("id", 1L, "nombre", "KPI actualizado");

        when(kpiClient.actualizarIndicador(1L, request)).thenReturn(actualizado);

        Object resultado = controller.actualizarIndicador(1L, request);

        assertEquals(actualizado, resultado);
        verify(kpiClient).actualizarIndicador(1L, request);
    }

    @Test
    void eliminarIndicador_debeDelegarEnKpiClient() {
        KpiClient kpiClient = mock(KpiClient.class);
        KpiController controller = new KpiController(kpiClient);

        controller.eliminarIndicador(1L);

        verify(kpiClient).eliminarIndicador(1L);
    }
}
