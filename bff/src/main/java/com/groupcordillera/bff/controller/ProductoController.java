package com.groupcordillera.bff.controller;

import com.groupcordillera.bff.service.ProductoClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bff/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    private final ProductoClient productoClient;

    public ProductoController(ProductoClient productoClient) {
        this.productoClient = productoClient;
    }

    @GetMapping
    public Object obtenerProductos() {
        return productoClient.obtenerProductos();
    }

    @GetMapping("/{id}")
    public Object obtenerProductoPorId(@PathVariable Long id) {
        return productoClient.obtenerProductoPorId(id);
    }

    @PostMapping
    public Object crearProducto(@RequestBody Object producto) {
        return productoClient.crearProducto(producto);
    }

    @PutMapping("/{id}")
    public String actualizarProducto(@PathVariable Long id, @RequestBody Object producto) {
        productoClient.actualizarProducto(id, producto);
        return "Producto actualizado correctamente";
    }

    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoClient.eliminarProducto(id);
        return "Producto eliminado correctamente";
    }

    @GetMapping("/stock-bajo")
    public Object obtenerStockBajo() {
        return productoClient.obtenerStockBajo();
    }
}