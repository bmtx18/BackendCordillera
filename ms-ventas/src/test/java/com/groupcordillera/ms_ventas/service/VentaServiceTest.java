package com.groupcordillera.ms_ventas.service;

import com.groupcordillera.ms_ventas.model.Venta;
import com.groupcordillera.ms_ventas.repository.PagoVentaRepository;
import com.groupcordillera.ms_ventas.repository.VentaRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VentaServiceTest {

    @Test
    void listarVentas_debeRetornarLista() {

        VentaRepository ventaRepository = mock(VentaRepository.class);
        PagoVentaRepository pagoVentaRepository = mock(PagoVentaRepository.class);

        VentaService service =
                new VentaService(ventaRepository, pagoVentaRepository);

        Venta venta = new Venta();
        venta.setId(1L);

        when(ventaRepository.findAll())
                .thenReturn(List.of(venta));

        List<Venta> resultado = service.listarVentas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());

        verify(ventaRepository, times(1)).findAll();
    }

    @Test
    void buscarPorId_debeRetornarVenta() {

        VentaRepository ventaRepository = mock(VentaRepository.class);
        PagoVentaRepository pagoVentaRepository = mock(PagoVentaRepository.class);

        VentaService service =
                new VentaService(ventaRepository, pagoVentaRepository);

        Venta venta = new Venta();
        venta.setId(1L);

        when(ventaRepository.findById(1L))
                .thenReturn(Optional.of(venta));

        Venta resultado = service.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());

        verify(ventaRepository, times(1)).findById(1L);
    }

    @Test
    void buscarPorId_cuandoNoExiste_debeLanzarExcepcion() {

        VentaRepository ventaRepository = mock(VentaRepository.class);
        PagoVentaRepository pagoVentaRepository = mock(PagoVentaRepository.class);

        VentaService service =
                new VentaService(ventaRepository, pagoVentaRepository);

        when(ventaRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                RuntimeException.class,
                () -> service.buscarPorId(99L)
        );
    }
}