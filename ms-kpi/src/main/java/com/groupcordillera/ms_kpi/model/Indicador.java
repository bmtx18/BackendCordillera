package com.groupcordillera.ms_kpi.model;

import jakarta.persistence.*;

@Entity
public class Indicador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Double valorActual;
    private Double meta;
    private String unidadMedida;
    private String estado;
    private String area;

    public Indicador() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getValorActual() {
        return valorActual;
    }

    public void setValorActual(Double valorActual) {
        this.valorActual = valorActual;
    }

    public Double getMeta() {
        return meta;
    }

    public void setMeta(Double meta) {
        this.meta = meta;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}