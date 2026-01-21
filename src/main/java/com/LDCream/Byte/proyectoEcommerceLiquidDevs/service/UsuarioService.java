package com.LDCream.Byte.proyectoEcommerceLiquidDevs.service;

import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.Usuario;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.repository.IusuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UsuarioService implements IusuarioService{
    private final IusuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(IusuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
        /*Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        return Optional.of(usuario);*/
    }

    @Override
    public Usuario guardar(Usuario usuario) {

        if (usuario.getPassword_hash() != null && !usuario.getPassword_hash().startsWith("$")) {
            usuario.setPassword_hash(hashPassword(usuario.getPassword_hash()));
        }

        // Establecer fecha de registro si es nuevo
        if (usuario.getId() == null) {
            usuario.setFechaRegistro(LocalDateTime.now());
            usuario.setActivo(true);
        }

        return usuarioRepository.save(usuario);

    }

    @Override
    public void eliminarPorId(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public void editarUsuario(Long id, Usuario usuarioActualizado) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if (usuarioOpt.isPresent()) {
            Usuario usuarioExistente = usuarioOpt.get();

            usuarioExistente.setNombre(usuarioActualizado.getNombre());
            usuarioExistente.setApellido(usuarioActualizado.getApellido());
            usuarioExistente.setEmail(usuarioActualizado.getEmail());
            usuarioExistente.setTelefono(usuarioActualizado.getTelefono());


            if (usuarioActualizado.getPassword_hash() != null &&
                    !usuarioActualizado.getPassword_hash().isEmpty()) {
                usuarioExistente.setPassword_hash(hashPassword(usuarioActualizado.getPassword_hash()));
            }

            usuarioRepository.save(usuarioExistente);
        }
        /*Usuario usuarioExistente = usuarioRepository.findById(id).orElse(null);
        if (usuarioExistente != null) {
            usuarioExistente.setNombre(usuarioActualizado.getNombre());
            usuarioExistente.setApellido(usuarioActualizado.getApellido());
            usuarioExistente.setEmail(usuarioActualizado.getEmail());
            usuarioExistente.setTelefono(usuarioActualizado.getTelefono());
            usuarioExistente.setPassword_hash(usuarioActualizado.getPassword_hash());
        }*/
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public boolean existeEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    @Override
    public boolean verificarPassword(String passwordPlano, String passwordHash) {
        String hashedInput = hashPassword(passwordPlano);
        return hashedInput.equals(passwordHash);
    }
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear password", e);
        }
    }
}

