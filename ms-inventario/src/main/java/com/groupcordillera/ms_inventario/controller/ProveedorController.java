package com.groupcordillera.ms_inventario.controller;

import com.groupcordillera.ms_inventario.model.Proveedor;
import com.groupcordillera.ms_inventario.repository.ProveedorRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proveedores")
@CrossOrigin(origins = "*")
public class ProveedorController {

    private final ProveedorRepository proveedorRepository;

    public ProveedorController(ProveedorRepository proveedorRepository) {
        this.proveedorRepository = proveedorRepository;
    }

    @GetMapping
    public List<Proveedor> listarProveedores() {
        return proveedorRepository.findAll();
    }

    @PostMapping
    public Proveedor crearProveedor(@RequestBody Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }
}