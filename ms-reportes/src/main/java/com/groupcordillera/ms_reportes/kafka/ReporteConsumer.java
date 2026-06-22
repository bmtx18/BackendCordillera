package com.groupcordillera.ms_reportes.kafka;

import com.groupcordillera.ms_reportes.model.Reporte;
import com.groupcordillera.ms_reportes.model.ReporteUsuario;
import com.groupcordillera.ms_reportes.repository.ReporteRepository;
import com.groupcordillera.ms_reportes.repository.ReporteUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReporteConsumer {

    private final ReporteRepository reporteRepository;
    private final ReporteUsuarioRepository reporteUsuarioRepository;

    @KafkaListener(
            topics = "usuario-events",
            groupId = "reportes-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void consumirEventoUsuario(UsuarioEvent evento) {

        ReporteUsuario reporteUsuario = new ReporteUsuario();
        reporteUsuario.setUsuarioId(evento.getUsuarioId());
        reporteUsuario.setNombre(evento.getNombre());
        reporteUsuario.setCorreo(evento.getCorreo());
        reporteUsuario.setRol(evento.getRol());
        reporteUsuario.setAccion(evento.getAccion());

        reporteUsuarioRepository.save(reporteUsuario);

        Reporte reporte = new Reporte();
            reporte.setTipo("USUARIO");
            reporte.setDescripcion("Evento de usuario: " + evento.getAccion());
            reporte.setClienteNombre(evento.getNombre());
            reporte.setClienteCorreo(evento.getCorreo());
            reporte.setEstado("Pendiente");
            reporte.setRespuestaAdmin("");
            reporte.setFechaCreacion(java.time.LocalDateTime.now().toString());
            reporte.setFechaRespuesta("");

            reporteRepository.save(reporte);
    }
}