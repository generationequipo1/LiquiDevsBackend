package com.LDCream.Byte.proyectoEcommerceLiquidDevs.service;

import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.Producto;

import java.util.List;

public interface IproductoService {

    List<Producto> listarProductos();
    Producto buscarProductoPorId(Integer id);
    Producto guardarProducto(Producto producto);
    void eliminarProducto(Integer id);
}
