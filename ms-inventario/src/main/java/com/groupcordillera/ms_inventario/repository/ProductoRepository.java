package com.groupcordillera.ms_inventario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.groupcordillera.ms_inventario.model.Producto;

import java.util.List;


public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByStockLessThanEqual(Integer stock);
}