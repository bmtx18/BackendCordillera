package com.groupcordillera.ms_ventas.controller;

import com.groupcordillera.ms_ventas.dto.IndicadorVentaDTO;
import com.groupcordillera.ms_ventas.dto.VentaRequest;
import com.groupcordillera.ms_ventas.model.Venta;
import com.groupcordillera.ms_ventas.service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ventas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VentaController {

    private final VentaService ventaService;

    // ── CREAR ─────────────────────────────────────────────────────────────────

    @PostMapping
    public Venta crearVenta(@RequestBody VentaRequest request) {
        return ventaService.crearVenta(request);
    }

    // ── LISTAR TODAS ──────────────────────────────────────────────────────────

    @GetMapping
    public List<Venta> listarVentas() {
        return ventaService.listarVentas();
    }

    // ── BUSCAR POR ID ─────────────────────────────────────────────────────────

    @GetMapping("/{id}")
    public Venta buscarPorId(@PathVariable Long id) {
        return ventaService.buscarPorId(id);
    }

    // ── ELIMINAR ──────────────────────────────────────────────────────────────

    @DeleteMapping("/{id}")
    public String eliminarVenta(@PathVariable Long id) {
        ventaService.eliminarVenta(id);
        return "Venta eliminada correctamente";
    }

    // ── DESPACHOS DE UN DRIVER ────────────────────────────────────────────────

    @GetMapping("/driver/{driverId}")
    public List<Venta> despachosPorDriver(@PathVariable Long driverId) {
        return ventaService.listarDespachosPorDriver(driverId);
    }

    // ── ASIGNAR DRIVER A UNA VENTA ────────────────────────────────────────────

    @PatchMapping("/{id}/asignar-driver")
    public Venta asignarDriver(
            @PathVariable Long id,
            @RequestParam Long driverId
    ) {
        return ventaService.asignarDriver(id, driverId);
    }

    @GetMapping("/cliente/{correo}")
    public List<Venta> ventasPorCliente(@PathVariable String correo) {
        return ventaService.listarVentasPorCliente(correo);
    }

    // ── CAMBIAR ESTADO DE DESPACHO ────────────────────────────────────────────

    // Estados válidos: PENDIENTE, ASIGNADO, EN_CAMINO, ENTREGADO
    @PatchMapping("/{id}/estado-despacho")
    public Venta cambiarEstadoDespacho(
            @PathVariable Long id,
            @RequestParam String estado
    ) {
        return ventaService.cambiarEstadoDespacho(id, estado);
    }

    // ── INDICADORES DE VENTAS ─────────────────────────────────────────────────

    @GetMapping("/indicadores/productos")
    public List<IndicadorVentaDTO> indicadoresPorProducto() {
        return ventaService.obtenerIndicadoresPorProducto();
    }
}
