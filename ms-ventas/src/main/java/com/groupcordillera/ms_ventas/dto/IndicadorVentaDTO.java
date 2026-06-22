package com.groupcordillera.ms_ventas.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IndicadorVentaDTO {

    private String nombreProducto;
    private Integer cantidadVendida;
    private Double porcentaje;
    private Double totalRecaudado;
}
