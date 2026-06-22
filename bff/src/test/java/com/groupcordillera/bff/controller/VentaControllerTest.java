package com.groupcordillera.bff.controller;

import com.groupcordillera.bff.service.VentaClient;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VentaControllerTest {

    @Test
    void obtenerVentas_debeRetornarVentas() {
        VentaClient ventaClient = mock(VentaClient.class);
        VentaController controller = new VentaController(ventaClient);
        Object ventas = List.of(Map.of("id", 1L));

        when(ventaClient.obtenerVentas()).thenReturn(ventas);

        Object resultado = controller.obtenerVentas();

        assertEquals(ventas, resultado);
        verify(ventaClient).obtenerVentas();
    }

    @Test
    void obtenerVentasPorCliente_debeDelegarEnVentaClient() {
        VentaClient ventaClient = mock(VentaClient.class);
        VentaController controller = new VentaController(ventaClient);
        Object ventas = List.of(Map.of("clienteCorreo", "cliente@test.cl"));

        when(ventaClient.obtenerVentasPorCliente("cliente@test.cl")).thenReturn(ventas);

        Object resultado = controller.obtenerVentasPorCliente("cliente@test.cl");

        assertEquals(ventas, resultado);
        verify(ventaClient).obtenerVentasPorCliente("cliente@test.cl");
    }

    @Test
    void crearVenta_debeDelegarEnVentaClient() {
        VentaClient ventaClient = mock(VentaClient.class);
        VentaController controller = new VentaController(ventaClient);
        Object request = Map.of("clienteCorreo", "cliente@test.cl");
        Object creada = Map.of("id", 1L);

        when(ventaClient.crearVenta(request)).thenReturn(creada);

        Object resultado = controller.crearVenta(request);

        assertEquals(creada, resultado);
        verify(ventaClient).crearVenta(request);
    }

    @Test
    void eliminarVenta_debeRetornarMensajeCorrecto() {
        VentaClient ventaClient = mock(VentaClient.class);
        VentaController controller = new VentaController(ventaClient);

        String resultado = controller.eliminarVenta(1L);

        assertEquals("Venta eliminada correctamente", resultado);
        verify(ventaClient).eliminarVenta(1L);
    }
}
