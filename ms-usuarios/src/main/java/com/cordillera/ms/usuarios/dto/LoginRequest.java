package com.cordillera.ms.usuarios.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

    private String correo;

    private String password;
}