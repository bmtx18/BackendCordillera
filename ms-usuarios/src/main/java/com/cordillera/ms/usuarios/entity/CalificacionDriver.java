package com.cordillera.ms.usuarios.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "calificaciones_driver")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalificacionDriver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long driverId;

    private String clienteNombre;

    private String clienteCorreo;

    // Puntuacion del 1 al 5
    private Integer puntuacion;

    private String comentario;

    @Builder.Default
    private LocalDateTime fecha = LocalDateTime.now();
}
