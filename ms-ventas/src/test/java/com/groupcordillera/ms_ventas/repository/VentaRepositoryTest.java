package com.groupcordillera.ms_ventas.repository;

import com.groupcordillera.ms_ventas.model.Venta;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VentaRepositoryTest {

    @Autowired
    private VentaRepository ventaRepository;

    @Test
    void guardarVenta_debePersistirCorrectamente() {

        Venta venta = new Venta();

        venta.setClienteNombre("Benjamin");
        venta.setClienteCorreo("benjamin@test.com");
        venta.setComuna("Santiago");
        venta.setDireccionEntrega("Av Test 123");
        venta.setEstado("PAGADA");
        venta.setEstadoDespacho("PENDIENTE");
        venta.setRegion("Metropolitana");
        venta.setTelefonoContacto("999999999");
        venta.setSubtotal(10000.0);
        venta.setIva(1900.0);
        venta.setTotal(11900.0);
        venta.setUsuarioId(1L);
        venta.setDiasEstimadosEntrega(2);

        Venta guardada = ventaRepository.save(venta);

        assertNotNull(guardada.getId());
        assertEquals("Benjamin", guardada.getClienteNombre());
    }

    @Test
    void listarVentas_debeRetornarVentas() {

        Venta venta = new Venta();

        venta.setClienteNombre("Cliente Test");
        venta.setClienteCorreo("cliente@test.com");
        venta.setComuna("Santiago");
        venta.setDireccionEntrega("Calle Test 456");
        venta.setEstado("PAGADA");
        venta.setEstadoDespacho("PENDIENTE");
        venta.setRegion("Metropolitana");
        venta.setTelefonoContacto("988888888");
        venta.setSubtotal(5000.0);
        venta.setIva(950.0);
        venta.setTotal(5950.0);
        venta.setUsuarioId(2L);
        venta.setDiasEstimadosEntrega(2);

        ventaRepository.save(venta);

        assertFalse(ventaRepository.findAll().isEmpty());
    }
}