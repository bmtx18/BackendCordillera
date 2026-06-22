package com.groupcordillera.bff.controller;

import com.groupcordillera.bff.service.UsuarioClient;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bff/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    private final UsuarioClient usuarioClient;

    public UsuarioController(UsuarioClient usuarioClient) {
        this.usuarioClient = usuarioClient;
    }

    @GetMapping
    public Object listarUsuarios() {
        return usuarioClient.listarUsuarios();
    }

    @PostMapping("/registro")
    public Object registrar(@RequestBody Object usuario) {
        return usuarioClient.registrar(usuario);
    }

    @PostMapping("/login")
    public Object login(@RequestBody Object usuario) {
        return usuarioClient.login(usuario);
    }

    @GetMapping("/{id}")
    public Object buscarPorId(@PathVariable Long id) {
        return usuarioClient.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public String eliminar(@PathVariable Long id) {
        usuarioClient.eliminar(id);
        return "Usuario eliminado correctamente";
    }
}