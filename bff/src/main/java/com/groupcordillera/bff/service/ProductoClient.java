package com.groupcordillera.bff.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductoClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String URL = "http://localhost:8082/api/productos";

    public Object obtenerProductos() {
        return restTemplate.getForObject(URL, Object.class);
    }

    public Object obtenerProductoPorId(Long id) {
        return restTemplate.getForObject(URL + "/" + id, Object.class);
    }

    public Object crearProducto(Object producto) {
        return restTemplate.postForObject(URL, producto, Object.class);
    }

    public void actualizarProducto(Long id, Object producto) {
        restTemplate.put(URL + "/" + id, producto);
    }

    public void eliminarProducto(Long id) {
        restTemplate.delete(URL + "/" + id);
    }

    public Object obtenerStockBajo() {
        return restTemplate.getForObject(URL + "/stock-bajo", Object.class);
    }
}