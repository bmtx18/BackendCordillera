package com.groupcordillera.ms_ventas.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "detalle_ventas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Vínculo lógico con inventario/productos
    private Long productoId;

    private String nombreProducto;

    private Integer cantidad;

    private Double precioUnitario;

    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    @JsonIgnore
    private Venta venta;
}