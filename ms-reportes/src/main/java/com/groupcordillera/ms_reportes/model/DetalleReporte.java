package com.groupcordillera.ms_reportes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalle_reportes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetalleReporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoria;

    private String descripcion;

    private Double valorNumerico;

    @ManyToOne
    @JoinColumn(name = "reporte_id")
    @JsonIgnore
    private Reporte reporte;
}