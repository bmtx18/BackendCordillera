package com.groupcordillera.ms_reportes.controller;

import com.groupcordillera.ms_reportes.model.Reporte;
import com.groupcordillera.ms_reportes.repository.ReporteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reportes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReporteController {

    private final ReporteRepository reporteRepository;

    @GetMapping
    public List<Reporte> listarReportes() {
        return reporteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Reporte buscarPorId(@PathVariable Long id) {
        return reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
    }

    @GetMapping("/usuarios")
    public List<Reporte> listarReportesUsuarios() {
        return reporteRepository.findAll();
    }

    @PostMapping
    public Reporte crearReporte(@RequestBody Reporte reporte) {
        reporte.setEstado("Pendiente");
        reporte.setRespuestaAdmin("");
        reporte.setFechaCreacion(LocalDateTime.now().toString());
        reporte.setFechaRespuesta("");
        return reporteRepository.save(reporte);
    }

    @PostMapping("/usuarios")
    public Reporte crearReporteUsuario(@RequestBody Reporte reporte) {
        reporte.setEstado("Pendiente");
        reporte.setRespuestaAdmin("");
        reporte.setFechaCreacion(LocalDateTime.now().toString());
        reporte.setFechaRespuesta("");
        return reporteRepository.save(reporte);
    }

    @PutMapping("/{id}/responder")
    public Reporte responderReporte(
            @PathVariable Long id,
            @RequestBody Map<String, String> body
    ) {
        Reporte reporte = reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado"));

        reporte.setRespuestaAdmin(body.get("respuestaAdmin"));
        reporte.setEstado("Respondido");
        reporte.setFechaRespuesta(LocalDateTime.now().toString());

        return reporteRepository.save(reporte);
    }

    @DeleteMapping("/{id}")
    public void eliminarReporte(@PathVariable Long id) {
        reporteRepository.deleteById(id);
    }
}