package com.groupcordillera.ms_ventas.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pagos_venta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagoVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String metodoPago;
    // EFECTIVO, TARJETA, TRANSFERENCIA

    private Double montoPagado;

    private LocalDateTime fechaPago;

    private String estadoPago;
    // APROBADO, RECHAZADO, PENDIENTE

    @OneToOne
    @JoinColumn(name = "venta_id")
    @JsonIgnore
    private Venta venta;
}