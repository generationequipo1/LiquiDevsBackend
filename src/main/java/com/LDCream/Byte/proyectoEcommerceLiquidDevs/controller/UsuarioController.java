package com.LDCream.Byte.proyectoEcommerceLiquidDevs.controller;

import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.Usuario;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.service.IusuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
private final IusuarioService usuarioService;

    public UsuarioController(IusuarioService iusuarioService) {
        this.usuarioService = iusuarioService;
    }

    @GetMapping
    public List<Usuario> usuarios(){return usuarioService.buscarTodos();
    }
    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable Long id){
        return usuarioService.buscarPorId(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/crear")
    public Usuario crearUsuario(@RequestBody Usuario usuario){

         return usuarioService.guardar(usuario);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarPorId(id);
    }

    @PutMapping("/{id}")
    public void editarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
        usuarioService.editarUsuario(id, usuarioActualizado);
    }


}
