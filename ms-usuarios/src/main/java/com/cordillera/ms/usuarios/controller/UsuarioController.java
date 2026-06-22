package com.cordillera.ms.usuarios.controller;

import com.cordillera.ms.usuarios.dto.CalificacionRequest;
import com.cordillera.ms.usuarios.dto.LoginRequest;
import com.cordillera.ms.usuarios.dto.RegistroRequest;
import com.cordillera.ms.usuarios.entity.CalificacionDriver;
import com.cordillera.ms.usuarios.entity.Usuario;
import com.cordillera.ms.usuarios.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioService usuarioService;

    // ── REGISTRO ─────────────────────────────────────────────────────────────

    @PostMapping("/registro")
    public Usuario registrar(@RequestBody RegistroRequest request) {
        return usuarioService.registrar(request);
    }

    // ── LOGIN ─────────────────────────────────────────────────────────────────

    @PostMapping("/login")
    public Usuario login(@RequestBody LoginRequest request) {
        return usuarioService.login(request);
    }

    // ── LISTAR TODOS ──────────────────────────────────────────────────────────

    @GetMapping
    public List<Usuario> listar() {
        return usuarioService.listarUsuarios();
    }

    // ── LISTAR DRIVERS ────────────────────────────────────────────────────────

    @GetMapping("/drivers")
    public List<Usuario> listarDrivers() {
        return usuarioService.listarDrivers();
    }

    // ── BUSCAR POR ID ─────────────────────────────────────────────────────────

    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    // ── ELIMINAR ──────────────────────────────────────────────────────────────

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return "Usuario eliminado correctamente";
    }

    // ── CAMBIAR ESTADO ────────────────────────────────────────────────────────

    @PatchMapping("/{id}/estado")
    public Usuario cambiarEstado(
            @PathVariable Long id,
            @RequestParam Boolean activo
    ) {
        return usuarioService.cambiarEstado(id, activo);
    }

    // ── CALIFICAR DRIVER ──────────────────────────────────────────────────────

    @PostMapping("/calificaciones")
    public CalificacionDriver calificarDriver(@RequestBody CalificacionRequest request) {
        return usuarioService.calificarDriver(request);
    }

    // ── VER CALIFICACIONES DE UN DRIVER ───────────────────────────────────────

    @GetMapping("/calificaciones/driver/{driverId}")
    public List<CalificacionDriver> verCalificacionesDriver(@PathVariable Long driverId) {
        return usuarioService.obtenerCalificacionesDriver(driverId);
    }
}
