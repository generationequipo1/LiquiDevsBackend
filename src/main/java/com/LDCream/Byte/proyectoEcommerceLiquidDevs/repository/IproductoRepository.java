package com.LDCream.Byte.proyectoEcommerceLiquidDevs.repository;


import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IproductoRepository extends JpaRepository<Producto, Integer> {
}