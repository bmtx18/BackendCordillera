package com.groupcordillera.ms_inventario.repository;

import com.groupcordillera.ms_inventario.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<Proveedor, Long> {
}