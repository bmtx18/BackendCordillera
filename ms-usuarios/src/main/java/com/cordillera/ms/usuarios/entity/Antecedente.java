package com.cordillera.ms.usuarios.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "antecedentes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Antecedente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer comprasRealizadas;

    private Integer reportesRealizados;

    private Integer aniosActivo;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}