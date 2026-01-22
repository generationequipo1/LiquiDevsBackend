package com.LDCream.Byte.proyectoEcommerceLiquidDevs.service;

import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.Producto;

import java.util.List;
import java.util.Optional;

public interface IproductoService {

    List<Producto> findAll();
    Optional<Producto> findById(Long id);
    Producto save(Producto producto);
    void deleteById(Long id);
    List<Producto> findByNombreContaining(String nombre);
}
    /*List<Producto> listarProductos();
    Producto buscarProductoPorId(Integer id);
    Producto buscarProductoPorId(Long id);
    Producto guardarProducto(Producto producto);
    void eliminarProducto(Integer id);
    void eliminarProducto(Long id);
    Producto actualizarStock(Integer id, Integer cantidad);*/



