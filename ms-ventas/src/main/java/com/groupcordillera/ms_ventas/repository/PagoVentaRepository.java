package com.groupcordillera.ms_ventas.repository;

import com.groupcordillera.ms_ventas.model.PagoVenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoVentaRepository extends JpaRepository<PagoVenta, Long> {
}