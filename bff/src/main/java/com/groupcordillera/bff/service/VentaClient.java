package com.groupcordillera.bff.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class VentaClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String URL = "http://localhost:8084/api/ventas";

    public Object obtenerVentas() {
        return restTemplate.getForObject(URL, Object.class);
    }

    public Object obtenerVentaPorId(Long id) {
        return restTemplate.getForObject(URL + "/" + id, Object.class);
    }

    public Object crearVenta(Object venta) {
        return restTemplate.postForObject(URL, venta, Object.class);
    }

    public void eliminarVenta(Long id) {
        restTemplate.delete(URL + "/" + id);
    }

    // Despachos de un driver
    public Object obtenerDespachosPorDriver(Long driverId) {
        return restTemplate.getForObject(URL + "/driver/" + driverId, Object.class);
    }

    // Indicadores de ventas por producto
    public Object obtenerIndicadoresProductos() {
        return restTemplate.getForObject(URL + "/indicadores/productos", Object.class);
    }

    public Object obtenerVentasPorCliente(String correo) {
        return restTemplate.getForObject(URL + "/cliente/" + correo, Object.class);
    }
}
