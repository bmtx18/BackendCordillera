package com.groupcordillera.bff.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardService {

    private final VentaClient ventaClient;
    private final ProductoClient productoClient;

    public DashboardService(VentaClient ventaClient, ProductoClient productoClient) {
        this.ventaClient = ventaClient;
        this.productoClient = productoClient;
    }

    // Dashboard general (ventas + productos + stock bajo)
    public Map<String, Object> obtenerDashboard() {

        Object ventas = ventaClient.obtenerVentas();
        Object productos = productoClient.obtenerProductos();
        Object stockBajo = productoClient.obtenerStockBajo();

        Map<String, Object> response = new HashMap<>();
        response.put("ventas", ventas);
        response.put("productos", productos);
        response.put("stockBajo", stockBajo);

        return response;
    }

    // Indicadores de ventas agrupados por producto
    public Object obtenerIndicadoresProductos() {
        return ventaClient.obtenerIndicadoresProductos();
    }
}
