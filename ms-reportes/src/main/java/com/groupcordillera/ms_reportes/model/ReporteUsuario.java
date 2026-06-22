package com.groupcordillera.ms_reportes.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reportes_usuarios")
public class ReporteUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;
    private String nombre;
    private String correo;
    private String rol;
    private String accion;
    private LocalDateTime fechaEvento;

    @PrePersist
    public void prePersist() {
        this.fechaEvento = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public LocalDateTime getFechaEvento() {
        return fechaEvento;
    }
}