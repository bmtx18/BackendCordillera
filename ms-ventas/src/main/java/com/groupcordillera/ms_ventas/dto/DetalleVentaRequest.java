package com.groupcordillera.ms_ventas.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetalleVentaRequest {

    private Long productoId;

    private String nombreProducto;

    private Integer cantidad;

    private Double precioUnitario;
}