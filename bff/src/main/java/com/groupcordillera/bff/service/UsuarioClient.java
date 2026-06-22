package com.groupcordillera.bff.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UsuarioClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String URL = "http://localhost:8085/api/usuarios";

    public Object obtenerUsuarios() {
        return restTemplate.getForObject(URL, Object.class);
    }

    public Object listarUsuarios() {
        return restTemplate.getForObject(URL, Object.class);
    }

    public Object obtenerUsuarioPorId(Long id) {
        return restTemplate.getForObject(URL + "/" + id, Object.class);
    }

    public Object buscarPorId(Long id) {
        return restTemplate.getForObject(URL + "/" + id, Object.class);
    }

    public Object registrar(Object usuario) {
        return restTemplate.postForObject(URL + "/registro", usuario, Object.class);
    }

    public Object login(Object usuario) {
        return restTemplate.postForObject(URL + "/login", usuario, Object.class);
    }

    public void eliminar(Long id) {
        restTemplate.delete(URL + "/" + id);
    }

    // Listar todos los drivers
    public Object obtenerDrivers() {
        return restTemplate.getForObject(URL + "/drivers", Object.class);
    }

    // Calificaciones de un driver
    public Object obtenerCalificacionesDriver(Long driverId) {
        return restTemplate.getForObject(URL + "/calificaciones/driver/" + driverId, Object.class);
    }

    // Calificar a un driver
    public Object calificarDriver(Object request) {
        return restTemplate.postForObject(URL + "/calificaciones", request, Object.class);
    }
}
