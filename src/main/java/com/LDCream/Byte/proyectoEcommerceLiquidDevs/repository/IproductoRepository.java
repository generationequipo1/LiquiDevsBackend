package com.LDCream.Byte.proyectoEcommerceLiquidDevs.repository;


import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IproductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByNombreContainingIgnoreCase(String nombre);
    List<Producto> findByCategoria(String categoria);
    List<Producto> findByEstadoTrue();
}