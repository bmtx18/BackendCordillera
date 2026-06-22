package com.groupcordillera.bff.controller;

import com.groupcordillera.bff.service.DashboardService;
import com.groupcordillera.bff.service.UsuarioClient;
import com.groupcordillera.bff.service.VentaClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/bff/dashboard")
@CrossOrigin(origins = "*")
public class DashboardController {

    private final DashboardService dashboardService;
    private final UsuarioClient usuarioClient;
    private final VentaClient ventaClient;

    public DashboardController(
            DashboardService dashboardService,
            UsuarioClient usuarioClient,
            VentaClient ventaClient
    ) {
        this.dashboardService = dashboardService;
        this.usuarioClient = usuarioClient;
        this.ventaClient = ventaClient;
    }

    // Dashboard general
    @GetMapping
    public Map<String, Object> obtenerDashboard() {
        return dashboardService.obtenerDashboard();
    }

    // Indicadores de ventas por producto (para gráficos)
    @GetMapping("/indicadores")
    public Object obtenerIndicadores() {
        return dashboardService.obtenerIndicadoresProductos();
    }

    // Listado de drivers (para asignación de despachos)
    @GetMapping("/drivers")
    public Object listarDrivers() {
        return usuarioClient.obtenerDrivers();
    }

    // Despachos asignados a un driver
    @GetMapping("/drivers/{driverId}/despachos")
    public Object despachosPorDriver(@PathVariable Long driverId) {
        return ventaClient.obtenerDespachosPorDriver(driverId);
    }

    // Calificaciones de un driver
    @GetMapping("/drivers/{driverId}/calificaciones")
    public Object calificacionesDriver(@PathVariable Long driverId) {
        return usuarioClient.obtenerCalificacionesDriver(driverId);
    }

    // Calificar a un driver
    @PostMapping("/drivers/calificar")
    public Object calificarDriver(@RequestBody Object request) {
        return usuarioClient.calificarDriver(request);
    }

    // Asignar driver a una venta
    @PatchMapping("/ventas/{ventaId}/asignar-driver")
    public Object asignarDriver(
            @PathVariable Long ventaId,
            @RequestParam Long driverId
    ) {
        return ventaClient.obtenerDespachosPorDriver(driverId);
    }

    // Cambiar estado de despacho
    @PatchMapping("/ventas/{ventaId}/estado-despacho")
    public Object cambiarEstado(
            @PathVariable Long ventaId,
            @RequestParam String estado
    ) {
        // Delega directo al ms-ventas
        org.springframework.web.client.RestTemplate rt = new org.springframework.web.client.RestTemplate();
        String url = "http://localhost:8084/api/ventas/" + ventaId + "/estado-despacho?estado=" + estado;
        rt.patchForObject(url, null, Object.class);
        return Map.of("mensaje", "Estado actualizado a " + estado);
    }
}
