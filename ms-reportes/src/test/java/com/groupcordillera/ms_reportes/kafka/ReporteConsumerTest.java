package com.groupcordillera.ms_reportes.kafka;

import com.groupcordillera.ms_reportes.model.Reporte;
import com.groupcordillera.ms_reportes.model.ReporteUsuario;
import com.groupcordillera.ms_reportes.repository.ReporteRepository;
import com.groupcordillera.ms_reportes.repository.ReporteUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReporteConsumerTest {

    @Test
    void consumirEventoUsuario_debeGuardarReporteUsuarioYReporteGeneral() {
        ReporteRepository reporteRepository = mock(ReporteRepository.class);
        ReporteUsuarioRepository reporteUsuarioRepository = mock(ReporteUsuarioRepository.class);
        ReporteConsumer consumer = new ReporteConsumer(reporteRepository, reporteUsuarioRepository);

        UsuarioEvent evento = new UsuarioEvent(10L, "Benjamin", "benjamin@test.com", "CLIENTE", "REGISTRO");

        consumer.consumirEventoUsuario(evento);

        ArgumentCaptor<ReporteUsuario> reporteUsuarioCaptor = ArgumentCaptor.forClass(ReporteUsuario.class);
        ArgumentCaptor<Reporte> reporteCaptor = ArgumentCaptor.forClass(Reporte.class);

        verify(reporteUsuarioRepository, times(1)).save(reporteUsuarioCaptor.capture());
        verify(reporteRepository, times(1)).save(reporteCaptor.capture());

        ReporteUsuario reporteUsuario = reporteUsuarioCaptor.getValue();
        assertEquals(10L, reporteUsuario.getUsuarioId());
        assertEquals("Benjamin", reporteUsuario.getNombre());
        assertEquals("benjamin@test.com", reporteUsuario.getCorreo());
        assertEquals("CLIENTE", reporteUsuario.getRol());
        assertEquals("REGISTRO", reporteUsuario.getAccion());

        Reporte reporte = reporteCaptor.getValue();
        assertEquals("USUARIO", reporte.getTipo());
        assertEquals("Evento de usuario: REGISTRO", reporte.getDescripcion());
        assertEquals("Benjamin", reporte.getClienteNombre());
        assertEquals("benjamin@test.com", reporte.getClienteCorreo());
        assertEquals("Pendiente", reporte.getEstado());
        assertEquals("", reporte.getRespuestaAdmin());
        assertNotNull(reporte.getFechaCreacion());
        assertEquals("", reporte.getFechaRespuesta());
    }
}
