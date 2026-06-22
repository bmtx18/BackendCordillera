package com.groupcordillera.ms_ventas.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class VentaRequest {

    private Long usuarioId;

    private String clienteNombre;

    private String clienteCorreo;

    private Long reporteId;

    private String metodoPago;

    // ── Datos de entrega (agregado para el checkout) ──────────────────────
    private String direccionEntrega;

    private String comuna;

    private String region;

    private String telefonoContacto;

    private List<DetalleVentaRequest> detalles;
}