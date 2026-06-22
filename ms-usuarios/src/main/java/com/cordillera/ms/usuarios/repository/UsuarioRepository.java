package com.cordillera.ms.usuarios.repository;

import com.cordillera.ms.usuarios.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCorreo(String correo);

    boolean existsByCorreo(String correo);

    // Buscar por rol (para listar drivers)
    List<Usuario> findByRol(String rol);
}
