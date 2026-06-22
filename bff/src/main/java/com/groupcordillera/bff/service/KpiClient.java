package com.groupcordillera.bff.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KpiClient {

    private final RestTemplate restTemplate = new RestTemplate();

    private final String URL_INDICADORES = "http://localhost:8083/api/indicadores";
    private final String URL_AREAS = "http://localhost:8083/api/areas";

    public Object listarIndicadores() {
        return restTemplate.getForObject(URL_INDICADORES, Object.class);
    }

    public Object obtenerIndicador(Long id) {
        return restTemplate.getForObject(URL_INDICADORES + "/" + id, Object.class);
    }

    public Object crearIndicador(Object indicador) {
        return restTemplate.postForObject(URL_INDICADORES, indicador, Object.class);
    }

    public Object actualizarIndicador(Long id, Object indicador) {
        restTemplate.put(URL_INDICADORES + "/" + id, indicador);
        return restTemplate.getForObject(URL_INDICADORES + "/" + id, Object.class);
    }

    public void eliminarIndicador(Long id) {
        restTemplate.delete(URL_INDICADORES + "/" + id);
    }

    public Object resumenIndicadores() {
        return restTemplate.getForObject(URL_INDICADORES + "/resumen", Object.class);
    }

    public Object listarAreas() {
        return restTemplate.getForObject(URL_AREAS, Object.class);
    }

    public Object crearArea(Object area) {
        return restTemplate.postForObject(URL_AREAS, area, Object.class);
    }
}