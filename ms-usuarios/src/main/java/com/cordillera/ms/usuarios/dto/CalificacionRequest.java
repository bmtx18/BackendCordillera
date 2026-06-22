package com.cordillera.ms.usuarios.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalificacionRequest {

    private Long driverId;

    private String clienteNombre;

    private String clienteCorreo;

    // Del 1 al 5
    private Integer puntuacion;

    private String comentario;
}
