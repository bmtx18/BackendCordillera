package com.groupcordillera.ms_reportes.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reportes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clienteNombre;

    private String clienteCorreo;

    private String tipo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String estado;

    @Column(columnDefinition = "TEXT")
    private String respuestaAdmin;

    private String fechaCreacion;

    private String fechaRespuesta;
}