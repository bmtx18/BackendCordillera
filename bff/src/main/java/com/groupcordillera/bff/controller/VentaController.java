package com.groupcordillera.bff.controller;

import com.groupcordillera.bff.service.VentaClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bff/ventas")
@CrossOrigin(origins = "*")
public class VentaController {

    private final VentaClient ventaClient;

    public VentaController(VentaClient ventaClient) {
        this.ventaClient = ventaClient;
    }

    @GetMapping
    public Object obtenerVentas() {
        return ventaClient.obtenerVentas();
    }

    @GetMapping("/cliente/{correo}")
    public Object obtenerVentasPorCliente(@PathVariable String correo) {
        return ventaClient.obtenerVentasPorCliente(correo);
    }

    @GetMapping("/{id}")
    public Object obtenerVentaPorId(@PathVariable Long id) {
        return ventaClient.obtenerVentaPorId(id);
    }

    @PostMapping
    public Object crearVenta(@RequestBody Object venta) {
        return ventaClient.crearVenta(venta);
    }

    @DeleteMapping("/{id}")
    public String eliminarVenta(@PathVariable Long id) {
        ventaClient.eliminarVenta(id);
        return "Venta eliminada correctamente";
    }
}