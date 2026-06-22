-- ============================================================
-- Script: crear_driver_prueba.sql
-- Uso: Ejecutar en la base de datos cordillera_usuarios
-- Crea un usuario con rol DRIVER para poder probar el módulo
-- de despachos y calificaciones.
-- ============================================================

USE cordillera_usuarios;

INSERT INTO usuarios (nombre, correo, password, rol, activo)
VALUES ('Juan Conductor', 'jc.22112017@gmail.com', 'agulucas889', 'DRIVER', true);

-- Verifica que se creó correctamente:
SELECT * FROM usuarios WHERE correo = 'jc.22112017@gmail.com';
