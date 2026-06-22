package com.groupcordillera.ms_ventas.service;

import com.groupcordillera.ms_ventas.dto.DetalleVentaRequest;
import com.groupcordillera.ms_ventas.dto.IndicadorVentaDTO;
import com.groupcordillera.ms_ventas.dto.VentaRequest;
import com.groupcordillera.ms_ventas.model.DetalleVenta;
import com.groupcordillera.ms_ventas.model.PagoVenta;
import com.groupcordillera.ms_ventas.model.Venta;
import com.groupcordillera.ms_ventas.repository.PagoVentaRepository;
import com.groupcordillera.ms_ventas.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository ventaRepository;
    private final PagoVentaRepository pagoVentaRepository;

    // ── CREAR VENTA ──────────────────────────────────────────────────────────

    public Venta crearVenta(VentaRequest request) {

        double subtotal = 0;
        List<DetalleVenta> detalles = new ArrayList<>();

        Venta venta = Venta.builder()
            .usuarioId(request.getUsuarioId())
            .clienteNombre(request.getClienteNombre())
            .clienteCorreo(request.getClienteCorreo())
            .reporteId(request.getReporteId())
            .fechaVenta(LocalDateTime.now())
            .estado("PAGADA")
            .estadoDespacho("PENDIENTE")
            .direccionEntrega(request.getDireccionEntrega())
            .comuna(request.getComuna())
            .region(request.getRegion())
            .telefonoContacto(request.getTelefonoContacto())
            .diasEstimadosEntrega(calcularDiasEntrega(request.getRegion()))
            .build();

        for (DetalleVentaRequest detalleRequest : request.getDetalles()) {

            double subtotalDetalle =
                    detalleRequest.getCantidad() *
                    detalleRequest.getPrecioUnitario();

            subtotal += subtotalDetalle;

            DetalleVenta detalle = DetalleVenta.builder()
                    .productoId(detalleRequest.getProductoId())
                    .nombreProducto(detalleRequest.getNombreProducto())
                    .cantidad(detalleRequest.getCantidad())
                    .precioUnitario(detalleRequest.getPrecioUnitario())
                    .subtotal(subtotalDetalle)
                    .venta(venta)
                    .build();

            detalles.add(detalle);
        }

        double iva = subtotal * 0.19;
        double total = subtotal + iva;

        venta.setSubtotal(subtotal);
        venta.setIva(iva);
        venta.setTotal(total);
        venta.setDetalles(detalles);

        Venta ventaGuardada = ventaRepository.save(venta);

        PagoVenta pago = PagoVenta.builder()
                .metodoPago(request.getMetodoPago())
                .montoPagado(total)
                .fechaPago(LocalDateTime.now())
                .estadoPago("APROBADO")
                .venta(ventaGuardada)
                .build();

        pagoVentaRepository.save(pago);
        ventaGuardada.setPago(pago);

        return ventaGuardada;
    }

    // ── CÁLCULO DE DÍAS DE ENTREGA (agregado para el checkout) ───────────────
    // Estimación simple basada en región; no reemplaza un cálculo logístico real.
    private Integer calcularDiasEntrega(String region) {

        if (region == null || region.isBlank()) {
            return 5;
        }

        String r = region.trim().toLowerCase();

        if (r.contains("metropolitana") || r.contains("santiago")) {
            return 2;
        }

        if (r.contains("valparaíso") || r.contains("valparaiso")
                || r.contains("o'higgins") || r.contains("ohiggins")
                || r.contains("maule") || r.contains("biobío") || r.contains("biobio")) {
            return 4;
        }

        // Regiones extremas (norte lejano / sur austral)
        if (r.contains("arica") || r.contains("tarapacá") || r.contains("tarapaca")
                || r.contains("magallanes") || r.contains("aysén") || r.contains("aysen")) {
            return 8;
        }

        return 6;
    }

    // ── LISTAR TODAS ─────────────────────────────────────────────────────────

    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    // ── BUSCAR POR ID ─────────────────────────────────────────────────────────

    public Venta buscarPorId(Long id) {
        return ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
    }

    // ── ELIMINAR ──────────────────────────────────────────────────────────────

    public void eliminarVenta(Long id) {
        Venta venta = buscarPorId(id);
        ventaRepository.delete(venta);
    }

    // ── DESPACHOS DE UN DRIVER ────────────────────────────────────────────────

    public List<Venta> listarDespachosPorDriver(Long driverId) {
        return ventaRepository.findByDriverId(driverId);
    }

    // ── ASIGNAR DRIVER A UNA VENTA ────────────────────────────────────────────

    public Venta asignarDriver(Long ventaId, Long driverId) {
        Venta venta = buscarPorId(ventaId);
        venta.setDriverId(driverId);
        venta.setEstadoDespacho("ASIGNADO");
        return ventaRepository.save(venta);
    }

    // ── CAMBIAR ESTADO DE DESPACHO ────────────────────────────────────────────

    public Venta cambiarEstadoDespacho(Long ventaId, String nuevoEstado) {
        Venta venta = buscarPorId(ventaId);
        venta.setEstadoDespacho(nuevoEstado);
        return ventaRepository.save(venta);
    }

    public List<Venta> listarVentasPorCliente(String correo) {
    return ventaRepository.findByClienteCorreo(correo);
    }

    // ── INDICADORES DE VENTAS POR PRODUCTO ───────────────────────────────────

    public List<IndicadorVentaDTO> obtenerIndicadoresPorProducto() {

        List<Venta> todasLasVentas = ventaRepository.findAll();

        // Agrupar detalles por nombreProducto
        Map<String, Integer> cantidadPorProducto = new HashMap<>();
        Map<String, Double> recaudadoPorProducto = new HashMap<>();

        for (Venta venta : todasLasVentas) {
            if (venta.getDetalles() == null) continue;
            for (var detalle : venta.getDetalles()) {
                String nombre = detalle.getNombreProducto();
                cantidadPorProducto.merge(nombre, detalle.getCantidad(), Integer::sum);
                recaudadoPorProducto.merge(nombre, detalle.getSubtotal(), Double::sum);
            }
        }

        int totalUnidades = cantidadPorProducto.values().stream()
                .mapToInt(Integer::intValue).sum();

        if (totalUnidades == 0) return Collections.emptyList();

        return cantidadPorProducto.entrySet().stream()
                .map(entry -> {
                    String nombre = entry.getKey();
                    int cantidad = entry.getValue();
                    double porcentaje = (cantidad * 100.0) / totalUnidades;
                    double recaudado = recaudadoPorProducto.getOrDefault(nombre, 0.0);

                    return IndicadorVentaDTO.builder()
                            .nombreProducto(nombre)
                            .cantidadVendida(cantidad)
                            .porcentaje(Math.round(porcentaje * 10.0) / 10.0)
                            .totalRecaudado(recaudado)
                            .build();
                })
                .sorted(Comparator.comparingInt(IndicadorVentaDTO::getCantidadVendida).reversed())
                .collect(Collectors.toList());
    }
}
