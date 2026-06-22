package com.groupcordillera.ms_reportes.kafka;

public class UsuarioEvent {

    private Long usuarioId;
    private String nombre;
    private String correo;
    private String rol;
    private String accion;

    public UsuarioEvent() {
    }

    public UsuarioEvent(Long usuarioId, String nombre, String correo, String rol, String accion) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
        this.accion = accion;
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
}