package com.groupcordillera.bff.controller;

import com.groupcordillera.bff.service.DashboardService;
import com.groupcordillera.bff.service.UsuarioClient;
import com.groupcordillera.bff.service.VentaClient;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DashboardControllerTest {

    @Test
    void obtenerDashboard_debeDelegarEnDashboardService() {
        DashboardService dashboardService = mock(DashboardService.class);
        UsuarioClient usuarioClient = mock(UsuarioClient.class);
        VentaClient ventaClient = mock(VentaClient.class);
        DashboardController controller = new DashboardController(dashboardService, usuarioClient, ventaClient);
        Map<String, Object> dashboard = Map.of("ventas", List.of(), "productos", List.of());

        when(dashboardService.obtenerDashboard()).thenReturn(dashboard);

        Map<String, Object> resultado = controller.obtenerDashboard();

        assertEquals(dashboard, resultado);
        verify(dashboardService).obtenerDashboard();
    }

    @Test
    void listarDrivers_debeDelegarEnUsuarioClient() {
        DashboardService dashboardService = mock(DashboardService.class);
        UsuarioClient usuarioClient = mock(UsuarioClient.class);
        VentaClient ventaClient = mock(VentaClient.class);
        DashboardController controller = new DashboardController(dashboardService, usuarioClient, ventaClient);
        Object drivers = List.of(Map.of("rol", "DRIVER"));

        when(usuarioClient.obtenerDrivers()).thenReturn(drivers);

        Object resultado = controller.listarDrivers();

        assertEquals(drivers, resultado);
        verify(usuarioClient).obtenerDrivers();
    }

    @Test
    void despachosPorDriver_debeDelegarEnVentaClient() {
        DashboardService dashboardService = mock(DashboardService.class);
        UsuarioClient usuarioClient = mock(UsuarioClient.class);
        VentaClient ventaClient = mock(VentaClient.class);
        DashboardController controller = new DashboardController(dashboardService, usuarioClient, ventaClient);
        Object despachos = List.of(Map.of("driverId", 7L));

        when(ventaClient.obtenerDespachosPorDriver(7L)).thenReturn(despachos);

        Object resultado = controller.despachosPorDriver(7L);

        assertEquals(despachos, resultado);
        verify(ventaClient).obtenerDespachosPorDriver(7L);
    }

    @Test
    void calificarDriver_debeDelegarEnUsuarioClient() {
        DashboardService dashboardService = mock(DashboardService.class);
        UsuarioClient usuarioClient = mock(UsuarioClient.class);
        VentaClient ventaClient = mock(VentaClient.class);
        DashboardController controller = new DashboardController(dashboardService, usuarioClient, ventaClient);
        Object request = Map.of("driverId", 7L, "puntuacion", 5);
        Object respuesta = Map.of("mensaje", "Calificación registrada");

        when(usuarioClient.calificarDriver(request)).thenReturn(respuesta);

        Object resultado = controller.calificarDriver(request);

        assertEquals(respuesta, resultado);
        verify(usuarioClient).calificarDriver(request);
    }
}
