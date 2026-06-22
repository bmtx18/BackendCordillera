package com.groupcordillera.ms_reportes.service;

import com.groupcordillera.ms_reportes.model.Reporte;
import com.groupcordillera.ms_reportes.repository.ReporteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteService {

    private final ReporteRepository reporteRepository;

    public ReporteService(ReporteRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    public List<Reporte> listarTodos() {
        return reporteRepository.findAll();
    }

    public List<Reporte> listarPorTipo(String tipo) {
        return reporteRepository.findByTipo(tipo.toUpperCase());
    }

    public Reporte buscarPorId(Long id) {
        return reporteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reporte no encontrado con id: " + id));
    }

    public Reporte guardarReporte(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    public void eliminarReporte(Long id) {
        reporteRepository.deleteById(id);
    }
}
