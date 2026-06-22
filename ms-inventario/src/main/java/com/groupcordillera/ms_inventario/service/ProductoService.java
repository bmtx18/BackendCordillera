package com.groupcordillera.ms_inventario.service;

import org.springframework.stereotype.Service;

import com.groupcordillera.ms_inventario.model.Producto;
import com.groupcordillera.ms_inventario.repository.ProductoRepository;

import java.util.List;

@Service

public class ProductoService {
     private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public Producto buscarPorId(Long id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
    }

    public Producto crearProducto(Producto producto) {
        producto.setId(null);
        return productoRepository.save(producto);
    }

    public Producto actualizarProducto(Long id, Producto productoActualizado) {
        Producto producto = buscarPorId(id);

        producto.setProveedor(productoActualizado.getProveedor());
        producto.setNombre(productoActualizado.getNombre());
        producto.setCategoria(productoActualizado.getCategoria());
        producto.setStock(productoActualizado.getStock());
        producto.setPrecio(productoActualizado.getPrecio());
        producto.setImagenUrl(productoActualizado.getImagenUrl());

        return productoRepository.save(producto);
    }

    public void eliminarProducto(Long id) {
        Producto producto = buscarPorId(id);
        productoRepository.delete(producto);
    }

    public List<Producto> listarStockBajo() {
        return productoRepository
        .findByStockLessThanEqual(5);
    }
    
    
}
