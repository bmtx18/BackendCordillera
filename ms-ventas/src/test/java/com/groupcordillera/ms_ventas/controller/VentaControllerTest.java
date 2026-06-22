package com.groupcordillera.ms_ventas.controller;

import com.groupcordillera.ms_ventas.model.Venta;
import com.groupcordillera.ms_ventas.service.VentaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(VentaController.class)
class VentaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private VentaService ventaService;

    @Test
    void listarVentas_debeRetornarOk() throws Exception {
        Venta venta = new Venta();
        venta.setId(1L);
        venta.setClienteNombre("Benjamin");
        venta.setClienteCorreo("benjamin@test.com");
        venta.setTotal(11900.0);

        when(ventaService.listarVentas()).thenReturn(List.of(venta));

        mockMvc.perform(get("/api/ventas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].clienteNombre").value("Benjamin"));
    }

    @Test
    void buscarPorId_debeRetornarOk() throws Exception {
        Venta venta = new Venta();
        venta.setId(1L);
        venta.setClienteNombre("Benjamin");

        when(ventaService.buscarPorId(1L)).thenReturn(venta);

        mockMvc.perform(get("/api/ventas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.clienteNombre").value("Benjamin"));
    }

    @Test
    void crearVenta_debeRetornarOk() throws Exception {
        Venta ventaCreada = new Venta();
        ventaCreada.setId(1L);
        ventaCreada.setClienteNombre("Benjamin");
        ventaCreada.setEstado("PAGADA");
        ventaCreada.setTotal(23800.0);

        when(ventaService.crearVenta(any())).thenReturn(ventaCreada);

        String json = """
                {
                  "usuarioId": 1,
                  "clienteNombre": "Benjamin",
                  "clienteCorreo": "benjamin@test.com",
                  "metodoPago": "TARJETA",
                  "region": "Metropolitana",
                  "comuna": "Santiago",
                  "direccionEntrega": "Av Test 123",
                  "telefonoContacto": "999999999",
                  "detalles": [
                    {
                      "productoId": 1,
                      "nombreProducto": "Teclado",
                      "cantidad": 2,
                      "precioUnitario": 10000.0
                    }
                  ]
                }
                """;

        mockMvc.perform(post("/api/ventas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.clienteNombre").value("Benjamin"))
                .andExpect(jsonPath("$.estado").value("PAGADA"));
    }

    @Test
    void eliminarVenta_debeRetornarOk() throws Exception {
        mockMvc.perform(delete("/api/ventas/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Venta eliminada correctamente"));

        verify(ventaService, times(1)).eliminarVenta(1L);
    }

    @Test
    void despachosPorDriver_debeRetornarOk() throws Exception {
        Venta venta = new Venta();
        venta.setId(1L);
        venta.setDriverId(10L);
        venta.setClienteNombre("Cliente Driver");

        when(ventaService.listarDespachosPorDriver(10L)).thenReturn(List.of(venta));

        mockMvc.perform(get("/api/ventas/driver/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].driverId").value(10));
    }

    @Test
    void asignarDriver_debeRetornarOk() throws Exception {
        Venta venta = new Venta();
        venta.setId(1L);
        venta.setDriverId(10L);
        venta.setEstadoDespacho("ASIGNADO");

        when(ventaService.asignarDriver(1L, 10L)).thenReturn(venta);

        mockMvc.perform(patch("/api/ventas/1/asignar-driver")
                        .param("driverId", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.driverId").value(10))
                .andExpect(jsonPath("$.estadoDespacho").value("ASIGNADO"));
    }

    @Test
    void cambiarEstadoDespacho_debeRetornarOk() throws Exception {
        Venta venta = new Venta();
        venta.setId(1L);
        venta.setEstadoDespacho("EN_CAMINO");

        when(ventaService.cambiarEstadoDespacho(1L, "EN_CAMINO")).thenReturn(venta);

        mockMvc.perform(patch("/api/ventas/1/estado-despacho")
                        .param("estado", "EN_CAMINO"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estadoDespacho").value("EN_CAMINO"));
    }
}