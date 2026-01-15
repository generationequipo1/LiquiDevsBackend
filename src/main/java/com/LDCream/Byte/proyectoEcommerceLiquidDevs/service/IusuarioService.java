package com.LDCream.Byte.proyectoEcommerceLiquidDevs.service;

import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IusuarioService {
List<Usuario> buscarTodos();
Optional<Usuario> buscarPorId(Long id);
Usuario guardar(Usuario usuario);
void eliminarPorId(Long id);
void editarUsuario(Long id, Usuario usuarioActualizado);

}
