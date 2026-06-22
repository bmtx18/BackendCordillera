package com.groupcordillera.ms_reportes.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReporteModelTest {

    @Test
    void builder_debeCrearReporteConDatos() {
        Reporte reporte = Reporte.builder()
                .id(1L)
                .clienteNombre("Benjamin")
                .clienteCorreo("benjamin@test.com")
                .tipo("RECLAMO")
                .descripcion("Producto defectuoso")
                .estado("Pendiente")
                .respuestaAdmin("")
                .fechaCreacion("2026-06-22T10:00:00")
                .fechaRespuesta("")
                .build();

        assertEquals(1L, reporte.getId());
        assertEquals("Benjamin", reporte.getClienteNombre());
        assertEquals("RECLAMO", reporte.getTipo());
        assertEquals("Pendiente", reporte.getEstado());
    }

    @Test
    void detalleReporte_debeGuardarDatosYRelacion() {
        Reporte reporte = new Reporte();
        reporte.setId(1L);

        DetalleReporte detalle = new DetalleReporte();
        detalle.setId(5L);
        detalle.setCategoria("VENTAS");
        detalle.setDescripcion("Total de reclamos");
        detalle.setValorNumerico(3.0);
        detalle.setReporte(reporte);

        assertEquals(5L, detalle.getId());
        assertEquals("VENTAS", detalle.getCategoria());
        assertEquals("Total de reclamos", detalle.getDescripcion());
        assertEquals(3.0, detalle.getValorNumerico());
        assertEquals(reporte, detalle.getReporte());
    }

    @Test
    void reporteUsuario_prePersist_debeAsignarFechaEvento() {
        ReporteUsuario reporteUsuario = new ReporteUsuario();
        reporteUsuario.setUsuarioId(10L);
        reporteUsuario.setNombre("Benjamin");
        reporteUsuario.setCorreo("benjamin@test.com");
        reporteUsuario.setRol("CLIENTE");
        reporteUsuario.setAccion("REGISTRO");

        reporteUsuario.prePersist();

        assertEquals(10L, reporteUsuario.getUsuarioId());
        assertEquals("Benjamin", reporteUsuario.getNombre());
        assertNotNull(reporteUsuario.getFechaEvento());
    }
}
