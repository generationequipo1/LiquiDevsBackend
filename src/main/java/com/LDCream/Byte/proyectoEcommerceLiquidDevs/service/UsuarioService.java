package com.LDCream.Byte.proyectoEcommerceLiquidDevs.service;

import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.Usuario;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.repository.IusuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UsuarioService implements IusuarioService{
    private final IusuarioRepository usuarioRepository;

    public UsuarioService(IusuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }
    //@Transactional(readOnly = true)
    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();

        return Optional.of(usuario);
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarPorId(Long id) {

    }

    @Override
    public void editarUsuario(Long id, Usuario usuarioActualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);
        if (usuarioExistente != null) {
            usuarioExistente.setNombre(usuarioActualizado.getNombre());
            usuarioExistente.setApellido(usuarioActualizado.getApellido());


        }
    }
}
