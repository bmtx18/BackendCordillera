package com.groupcordillera.bff.controller;

import com.groupcordillera.bff.service.KpiClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bff/kpi")
@CrossOrigin(origins = "*")
public class KpiController {

    private final KpiClient kpiClient;

    public KpiController(KpiClient kpiClient) {
        this.kpiClient = kpiClient;
    }

    @GetMapping("/indicadores")
    public Object listarIndicadores() {
        return kpiClient.listarIndicadores();
    }

    @GetMapping("/indicadores/{id}")
    public Object obtenerIndicador(@PathVariable Long id) {
        return kpiClient.obtenerIndicador(id);
    }

    @PostMapping("/indicadores")
    public Object crearIndicador(@RequestBody Object indicador) {
        return kpiClient.crearIndicador(indicador);
    }

    @PutMapping("/indicadores/{id}")
    public Object actualizarIndicador(@PathVariable Long id, @RequestBody Object indicador) {
        return kpiClient.actualizarIndicador(id, indicador);
    }

    @DeleteMapping("/indicadores/{id}")
    public void eliminarIndicador(@PathVariable Long id) {
        kpiClient.eliminarIndicador(id);
    }

    @GetMapping("/indicadores/resumen")
    public Object resumenIndicadores() {
        return kpiClient.resumenIndicadores();
    }

    @GetMapping("/areas")
    public Object listarAreas() {
        return kpiClient.listarAreas();
    }

    @PostMapping("/areas")
    public Object crearArea(@RequestBody Object area) {
        return kpiClient.crearArea(area);
    }
}