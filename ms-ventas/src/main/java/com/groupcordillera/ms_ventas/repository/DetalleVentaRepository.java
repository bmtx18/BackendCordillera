package com.groupcordillera.ms_ventas.repository;

import com.groupcordillera.ms_ventas.model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
}