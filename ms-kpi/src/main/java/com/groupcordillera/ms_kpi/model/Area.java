package com.groupcordillera.ms_kpi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "areas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}