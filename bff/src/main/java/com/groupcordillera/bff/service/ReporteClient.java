package com.groupcordillera.bff.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ReporteClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String URL = "http://localhost:8086/api/reportes";
    private final String URL_REPORTES = "http://localhost:8086/api/reportes";

    public Object listarReportes() {
        return restTemplate.getForObject(URL, Object.class);
    }

    public Object listarReportesUsuarios() {
        return restTemplate.getForObject(URL + "/usuarios", Object.class);
    }

    public Object crearReporteUsuarios(Object reporte) {
    return restTemplate.postForObject(URL_REPORTES + "/usuarios", reporte, Object.class);
}

    public Object buscarPorId(Long id) {
        return restTemplate.getForObject(URL + "/" + id, Object.class);
    }
}