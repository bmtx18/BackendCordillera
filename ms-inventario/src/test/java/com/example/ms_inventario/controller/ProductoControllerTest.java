package com.example.ms_inventario.controller;

import com.groupcordillera.ms_inventario.controller.ProductoController;
import com.groupcordillera.ms_inventario.model.Producto;
import com.groupcordillera.ms_inventario.service.ProductoService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoControllerTest {

    @Test
    void listarProductos_debeRetornarLista() {
        ProductoService service = mock(ProductoService.class);
        ProductoController controller = new ProductoController(service);

        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Plancha");

        when(service.listarProductos()).thenReturn(List.of(producto));

        List<Producto> resultado = controller.listarProductos();

        assertEquals(1, resultado.size());
        assertEquals("Plancha", resultado.get(0).getNombre());
    }

    @Test
    void buscarProducto_debeRetornarProducto() {
        ProductoService service = mock(ProductoService.class);
        ProductoController controller = new ProductoController(service);

        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Escoba");

        when(service.buscarPorId(1L)).thenReturn(producto);

        Producto resultado = controller.buscarProducto(1L);

        assertEquals(1L, resultado.getId());
        assertEquals("Escoba", resultado.getNombre());
    }

    @Test
    void crearProducto_debeGuardarProducto() {
        ProductoService service = mock(ProductoService.class);
        ProductoController controller = new ProductoController(service);

        Producto producto = new Producto();
        producto.setNombre("Lavadora");

        when(service.crearProducto(producto)).thenReturn(producto);

        Producto resultado = controller.crearProducto(producto);

        assertEquals("Lavadora", resultado.getNombre());
        verify(service, times(1)).crearProducto(producto);
    }

    @Test
    void actualizarProducto_debeActualizarProducto() {
        ProductoService service = mock(ProductoService.class);
        ProductoController controller = new ProductoController(service);

        Producto producto = new Producto();
        producto.setNombre("Refrigerador");

        when(service.actualizarProducto(1L, producto)).thenReturn(producto);

        Producto resultado = controller.actualizarProducto(1L, producto);

        assertEquals("Refrigerador", resultado.getNombre());
        verify(service, times(1)).actualizarProducto(1L, producto);
    }

    @Test
    void eliminarProducto_debeEliminarYRetornarMensaje() {
        ProductoService service = mock(ProductoService.class);
        ProductoController controller = new ProductoController(service);

        Map<String, String> resultado = controller.eliminarProducto(1L);

        assertEquals("Producto eliminado correctamente", resultado.get("mensaje"));
        verify(service, times(1)).eliminarProducto(1L);
    }

    @Test
    void listarStockBajo_debeRetornarProductosConStockBajo() {
        ProductoService service = mock(ProductoService.class);
        ProductoController controller = new ProductoController(service);

        Producto producto = new Producto();
        producto.setNombre("Cocina");
        producto.setStock(3);

        when(service.listarStockBajo()).thenReturn(List.of(producto));

        List<Producto> resultado = controller.listarStockBajo();

        assertEquals(1, resultado.size());
        assertEquals(3, resultado.get(0).getStock());
    }
}