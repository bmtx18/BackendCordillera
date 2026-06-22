package com.cordillera.ms.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackages = "com.cordillera.ms.usuarios"
)
public class MsUsuariosApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsUsuariosApplication.class, args);
    }
}