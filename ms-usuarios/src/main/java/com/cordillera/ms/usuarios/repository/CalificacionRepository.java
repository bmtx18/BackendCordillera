package com.cordillera.ms.usuarios.repository;

import com.cordillera.ms.usuarios.entity.CalificacionDriver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalificacionRepository extends JpaRepository<CalificacionDriver, Long> {

    List<CalificacionDriver> findByDriverId(Long driverId);
}
