package com.groupcordillera.ms_kpi.controller;

import com.groupcordillera.ms_kpi.model.Indicador;
import com.groupcordillera.ms_kpi.repository.IndicadorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/indicadores")
@CrossOrigin(origins = "*")
public class IndicadorController {

    private final IndicadorRepository indicadorRepository;

    public IndicadorController(IndicadorRepository indicadorRepository) {
        this.indicadorRepository = indicadorRepository;
    }

    @GetMapping
    public List<Indicador> listarIndicadores() {
        return indicadorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Indicador obtenerIndicador(@PathVariable Long id) {
        return indicadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Indicador no encontrado"));
    }

    @PostMapping
    public Indicador crearIndicador(@RequestBody Indicador indicador) {
        return indicadorRepository.save(indicador);
    }

    @PutMapping("/{id}")
    public Indicador actualizarIndicador(@PathVariable Long id, @RequestBody Indicador datos) {
        Indicador indicador = indicadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Indicador no encontrado"));

        indicador.setNombre(datos.getNombre());
        indicador.setDescripcion(datos.getDescripcion());
        indicador.setValorActual(datos.getValorActual());
        indicador.setMeta(datos.getMeta());
        indicador.setUnidadMedida(datos.getUnidadMedida());
        indicador.setEstado(datos.getEstado());
        indicador.setArea(datos.getArea());

        return indicadorRepository.save(indicador);
    }

    @DeleteMapping("/{id}")
    public void eliminarIndicador(@PathVariable Long id) {
        indicadorRepository.deleteById(id);
    }

    @GetMapping("/resumen")
    public Map<String, Object> resumenIndicadores() {
        List<Indicador> indicadores = indicadorRepository.findAll();

        long cumplidos = indicadores.stream()
                .filter(i -> i.getValorActual() != null && i.getMeta() != null)
                .filter(i -> i.getValorActual() >= i.getMeta())
                .count();

        return Map.of(
                "totalIndicadores", indicadores.size(),
                "cumplidos", cumplidos,
                "pendientes", indicadores.size() - cumplidos
        );
    }
}