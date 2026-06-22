package com.groupcordillera.bff.controller;

import com.groupcordillera.bff.service.ProductoClient;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoControllerTest {

    @Test
    void obtenerProductos_debeRetornarProductos() {
        ProductoClient productoClient = mock(ProductoClient.class);
        ProductoController controller = new ProductoController(productoClient);
        Object productos = List.of(Map.of("nombre", "Producto Test"));

        when(productoClient.obtenerProductos()).thenReturn(productos);

        Object resultado = controller.obtenerProductos();

        assertEquals(productos, resultado);
        verify(productoClient).obtenerProductos();
    }

    @Test
    void crearProducto_debeDelegarEnProductoClient() {
        ProductoClient productoClient = mock(ProductoClient.class);
        ProductoController controller = new ProductoController(productoClient);
        Object request = Map.of("nombre", "Mouse");
        Object creado = Map.of("id", 1L, "nombre", "Mouse");

        when(productoClient.crearProducto(request)).thenReturn(creado);

        Object resultado = controller.crearProducto(request);

        assertEquals(creado, resultado);
        verify(productoClient).crearProducto(request);
    }

    @Test
    void actualizarProducto_debeRetornarMensajeCorrecto() {
        ProductoClient productoClient = mock(ProductoClient.class);
        ProductoController controller = new ProductoController(productoClient);
        Object request = Map.of("nombre", "Mouse actualizado");

        String resultado = controller.actualizarProducto(1L, request);

        assertEquals("Producto actualizado correctamente", resultado);
        verify(productoClient).actualizarProducto(1L, request);
    }

    @Test
    void eliminarProducto_debeRetornarMensajeCorrecto() {
        ProductoClient productoClient = mock(ProductoClient.class);
        ProductoController controller = new ProductoController(productoClient);

        String resultado = controller.eliminarProducto(1L);

        assertEquals("Producto eliminado correctamente", resultado);
        verify(productoClient).eliminarProducto(1L);
    }
}
