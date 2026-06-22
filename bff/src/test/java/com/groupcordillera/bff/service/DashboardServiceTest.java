package com.groupcordillera.bff.service;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DashboardServiceTest {

    @Test
    void obtenerDashboard_debeRetornarVentasProductosYStockBajo() {
        VentaClient ventaClient = mock(VentaClient.class);
        ProductoClient productoClient = mock(ProductoClient.class);
        DashboardService dashboardService = new DashboardService(ventaClient, productoClient);

        List<String> ventas = List.of("venta1");
        List<String> productos = List.of("producto1");
        List<String> stockBajo = List.of("stock1");

        when(ventaClient.obtenerVentas()).thenReturn(ventas);
        when(productoClient.obtenerProductos()).thenReturn(productos);
        when(productoClient.obtenerStockBajo()).thenReturn(stockBajo);

        Map<String, Object> resultado = dashboardService.obtenerDashboard();

        assertEquals(ventas, resultado.get("ventas"));
        assertEquals(productos, resultado.get("productos"));
        assertEquals(stockBajo, resultado.get("stockBajo"));

        verify(ventaClient).obtenerVentas();
        verify(productoClient).obtenerProductos();
        verify(productoClient).obtenerStockBajo();
    }

    @Test
    void obtenerIndicadoresProductos_debeDelegarEnVentaClient() {
        VentaClient ventaClient = mock(VentaClient.class);
        ProductoClient productoClient = mock(ProductoClient.class);
        DashboardService dashboardService = new DashboardService(ventaClient, productoClient);

        Object indicadores = List.of(Map.of("producto", "Notebook", "cantidad", 3));
        when(ventaClient.obtenerIndicadoresProductos()).thenReturn(indicadores);

        Object resultado = dashboardService.obtenerIndicadoresProductos();

        assertEquals(indicadores, resultado);
        verify(ventaClient).obtenerIndicadoresProductos();
        verifyNoInteractions(productoClient);
    }
}
