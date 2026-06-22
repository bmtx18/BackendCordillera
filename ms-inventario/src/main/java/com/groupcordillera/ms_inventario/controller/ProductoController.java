package com.groupcordillera.ms_inventario.controller;

import org.springframework.web.bind.annotation.*;

import com.groupcordillera.ms_inventario.model.Producto;
import com.groupcordillera.ms_inventario.service.ProductoService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/productos")
@CrossOrigin(origins = "*")

public class ProductoController {
     private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    @GetMapping("/{id}")
    public Producto buscarProducto(@PathVariable Long id) {
        return productoService.buscarPorId(id);
    }

    @PostMapping
    public Producto crearProducto(@RequestBody Producto producto) {
        return productoService.crearProducto(producto);
    }

    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.actualizarProducto(id, producto);
    }

    @DeleteMapping("/{id}")
    public Map<String, String> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return Map.of("mensaje", "Producto eliminado correctamente");
    }

    @GetMapping("/stock-bajo")
    public List<Producto> listarStockBajo() {
        return productoService.listarStockBajo();
    }
    
}
