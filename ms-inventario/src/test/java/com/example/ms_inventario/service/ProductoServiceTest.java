package com.example.ms_inventario.service;

import com.groupcordillera.ms_inventario.model.Producto;
import com.groupcordillera.ms_inventario.repository.ProductoRepository;
import com.groupcordillera.ms_inventario.service.ProductoService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductoServiceTest {

    @Test
    void listarProductos_debeRetornarLista() {
        ProductoRepository repository = mock(ProductoRepository.class);
        ProductoService service = new ProductoService(repository);

        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Plancha");

        when(repository.findAll()).thenReturn(List.of(producto));

        List<Producto> resultado = service.listarProductos();

        assertEquals(1, resultado.size());
    }

    @Test
    void buscarPorId_debeRetornarProducto() {
        ProductoRepository repository = mock(ProductoRepository.class);
        ProductoService service = new ProductoService(repository);

        Producto producto = new Producto();
        producto.setId(1L);
        producto.setNombre("Escoba");

        when(repository.findById(1L)).thenReturn(Optional.of(producto));

        Producto resultado = service.buscarPorId(1L);

        assertEquals("Escoba", resultado.getNombre());
    }

    @Test
    void eliminarProducto_debeEliminarProducto() {
        ProductoRepository repository = mock(ProductoRepository.class);
        ProductoService service = new ProductoService(repository);

        Producto producto = new Producto();
        producto.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(producto));

        service.eliminarProducto(1L);

        verify(repository).delete(producto);
    }
}