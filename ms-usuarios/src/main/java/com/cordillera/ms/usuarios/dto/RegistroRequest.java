package com.cordillera.ms.usuarios.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistroRequest {

    private String nombre;

    private String correo;

    private String password;

    private String rol;
}
