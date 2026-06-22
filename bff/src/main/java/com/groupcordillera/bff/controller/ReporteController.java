package com.groupcordillera.bff.controller;

import com.groupcordillera.bff.service.ReporteClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bff/reportes")
@CrossOrigin(origins = "*")
public class ReporteController {

    private final ReporteClient reporteClient;

    public ReporteController(ReporteClient reporteClient) {
        this.reporteClient = reporteClient;
    }
    
    @PostMapping("/usuarios")
    public Object crearReporteUsuarios(@RequestBody Object reporte) {
        return reporteClient.crearReporteUsuarios(reporte);
    }

    @GetMapping
    public Object listarReportes() {
        return reporteClient.listarReportes();
    }

    @GetMapping("/usuarios")
    public Object listarReportesUsuarios() {
        return reporteClient.listarReportesUsuarios();
    }

    @GetMapping("/{id}")
    public Object buscarPorId(@PathVariable Long id) {
        return reporteClient.buscarPorId(id);
    }
}