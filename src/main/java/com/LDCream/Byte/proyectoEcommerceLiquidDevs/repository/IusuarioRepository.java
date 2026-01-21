package com.LDCream.Byte.proyectoEcommerceLiquidDevs.repository;

import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IusuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);

}
