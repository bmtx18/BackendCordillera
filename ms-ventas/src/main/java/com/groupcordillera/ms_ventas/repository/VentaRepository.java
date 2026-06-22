package com.groupcordillera.ms_ventas.repository;

import com.groupcordillera.ms_ventas.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {

    // Despachos asignados a un driver
    List<Venta> findByDriverId(Long driverId);
    List<Venta> findByClienteCorreo(String clienteCorreo);
}
