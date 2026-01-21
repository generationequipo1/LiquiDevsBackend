package com.LDCream.Byte.proyectoEcommerceLiquidDevs.controller;

import com.LDCream.Byte.proyectoEcommerceLiquidDevs.dto.LoginRequest;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.Usuario;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.service.IusuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/auth")
    @CrossOrigin(origins = "*")
    public class AuthController {

        private final IusuarioService usuarioService;

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
    }

