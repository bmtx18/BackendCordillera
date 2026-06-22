package com.groupcordillera.ms_kpi.repository;

import com.groupcordillera.ms_kpi.model.Indicador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndicadorRepository extends JpaRepository<Indicador, Long> {
}