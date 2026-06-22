package com.groupcordillera.ms_ventas.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "ventas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;

    private String clienteNombre;

    private String clienteCorreo;

    private Long reporteId;

    private LocalDateTime fechaVenta;

    private Double subtotal;

    private Double iva;

    private Double total;

    // Estado del pago: PAGADA, CANCELADA
    private String estado;

    // ID del driver asignado al despacho
    private Long driverId;

    // Estado del despacho: PENDIENTE, ASIGNADO, EN_CAMINO, ENTREGADO
    @Builder.Default
    private String estadoDespacho = "PENDIENTE";

    // ── Datos de entrega (agregado para el checkout) ──────────────────────
    private String direccionEntrega;

    private String comuna;

    private String region;

    private String telefonoContacto;

    // Días estimados de despacho calculados al momento de la compra
    private Integer diasEstimadosEntrega;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detalles;

    @OneToOne(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private PagoVenta pago;
}
