package com.LDCream.Byte.proyectoEcommerceLiquidDevs.controller;

import com.LDCream.Byte.proyectoEcommerceLiquidDevs.dto.LoginRequest;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.Usuario;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.service.IusuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
    @RequestMapping("/auth")
    @CrossOrigin(origins = "*")
    public class AuthController {
        @Autowired
        private IusuarioService usuarioService;


        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
            try {
                // Validaciones
                if (loginRequest.getEmail() == null || loginRequest.getEmail().trim().isEmpty()) {
                    return ResponseEntity.badRequest()
                            .body(crearRespuesta(false, "El email es obligatorio", null));
                }

                if (loginRequest.getPassword() == null || loginRequest.getPassword().trim().isEmpty()) {
                    return ResponseEntity.badRequest()
                            .body(crearRespuesta(false, "La contraseña es obligatoria", null));
                }

                // Buscar usuario por email
                Optional<Usuario> usuarioOpt = usuarioService.buscarPorEmail(loginRequest.getEmail());

                if (usuarioOpt.isEmpty()) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body(crearRespuesta(false, "Email o contraseña incorrectos", null));
                }

                Usuario usuario = usuarioOpt.get();

                // Verificar contraseña
                boolean passwordValido = usuarioService.verificarPassword(
                        loginRequest.getPassword(),
                        usuario.getPassword_hash()
                );

                if (!passwordValido) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                            .body(crearRespuesta(false, "Email o contraseña incorrectos", null));
                }

                // Verificar que el usuario esté activo
                if (!usuario.getActivo()) {
                    return ResponseEntity.status(HttpStatus.FORBIDDEN)
                            .body(crearRespuesta(false, "Usuario inactivo", null));
                }

                // Login exitoso - crear DTO sin password
                Map<String, Object> usuarioDTO = new HashMap<>();
                usuarioDTO.put("id", usuario.getId());
                usuarioDTO.put("nombre", usuario.getNombre());
                usuarioDTO.put("apellido", usuario.getApellido());
                usuarioDTO.put("email", usuario.getEmail());
                usuarioDTO.put("telefono", usuario.getTelefono());

                return ResponseEntity.ok(crearRespuesta(true, "Login exitoso", usuarioDTO));

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(crearRespuesta(false, "Error en el servidor: " + e.getMessage(), null));
            }
        }


        @PostMapping("/registro")
        public ResponseEntity<?> registro(@RequestBody Map<String, String> registroData) {
            try {
                // Extraer datos
                String nombre = registroData.get("nombre");
                String apellido = registroData.get("apellido");
                String email = registroData.get("email");
                String telefono = registroData.get("telefono");
                String password = registroData.get("password");
                String confirmarPassword = registroData.get("confirmarPassword");

                // Validaciones
                if (nombre == null || nombre.trim().isEmpty()) {
                    return ResponseEntity.badRequest()
                            .body(crearRespuesta(false, "El nombre es obligatorio", null));
                }

                if (apellido == null || apellido.trim().isEmpty()) {
                    return ResponseEntity.badRequest()
                            .body(crearRespuesta(false, "El apellido es obligatorio", null));
                }

                if (email == null || email.trim().isEmpty()) {
                    return ResponseEntity.badRequest()
                            .body(crearRespuesta(false, "El email es obligatorio", null));
                }

                if (password == null || password.length() < 6) {
                    return ResponseEntity.badRequest()
                            .body(crearRespuesta(false, "La contraseña debe tener al menos 6 caracteres", null));
                }

                if (!password.equals(confirmarPassword)) {
                    return ResponseEntity.badRequest()
                            .body(crearRespuesta(false, "Las contraseñas no coinciden", null));
                }

                // Verificar si el email ya existe
                if (usuarioService.existeEmail(email)) {
                    return ResponseEntity.status(HttpStatus.CONFLICT)
                            .body(crearRespuesta(false, "El email ya está registrado", null));
                }

                // Crear nuevo usuario
                Usuario nuevoUsuario = new Usuario();
                nuevoUsuario.setNombre(nombre);
                nuevoUsuario.setApellido(apellido);
                nuevoUsuario.setEmail(email);
                nuevoUsuario.setTelefono(telefono);
                nuevoUsuario.setPassword_hash(password); // El service lo hasheará

                Usuario usuarioGuardado = usuarioService.guardar(nuevoUsuario);


                Map<String, Object> usuarioDTO = new HashMap<>();
                usuarioDTO.put("id", usuarioGuardado.getId());
                usuarioDTO.put("nombre", usuarioGuardado.getNombre());
                usuarioDTO.put("apellido", usuarioGuardado.getApellido());
                usuarioDTO.put("email", usuarioGuardado.getEmail());
                usuarioDTO.put("telefono", usuarioGuardado.getTelefono());

                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(crearRespuesta(true, "Usuario registrado exitosamente", usuarioDTO));

            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(crearRespuesta(false, "Error en el servidor: " + e.getMessage(), null));
            }
        }


        //metodo de conssitencia
        private Map<String, Object> crearRespuesta(boolean success, String mensaje, Object data) {
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("success", success);
            respuesta.put("mensaje", mensaje);
            if (data != null) {
                respuesta.put("usuario", data);
            }
            return respuesta;
        }

    }

        /*private final IusuarioService usuarioService;

        public AuthController(IusuarioService usuarioService) {
            this.usuarioService = usuarioService;
        }
        @PostMapping("/register")
        @ResponseStatus(HttpStatus.CREATED)
        public Usuario register(@RequestBody Usuario usuario) {
            // Evitar email duplicado
            if (usuarioService.existeEmail(usuario.getEmail())) {
                throw new RuntimeException("El email ya está registrado");
            }
            return usuarioService.guardar(usuario);
        }

        @PostMapping("/login")
        public Usuario login(@RequestBody LoginRequest req) {

            Usuario user = usuarioService.buscarPorEmail(req.getEmail())
                    .orElseThrow(() -> new RuntimeException("Email no encontrado"));

            if (!user.getPassword_hash().equals(req.getPassword())) {
                throw new RuntimeException("Contraseña incorrecta");
            }

            return user;
        }
    }*/

