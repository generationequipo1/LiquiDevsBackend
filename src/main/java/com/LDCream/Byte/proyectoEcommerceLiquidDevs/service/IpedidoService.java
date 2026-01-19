package com.LDCream.Byte.proyectoEcommerceLiquidDevs.service;

import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.DetalleOrden;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.Pedido;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IpedidoService  {
    List<Pedido> buscarTodos();
    Pedido guardarPedido (Pedido pedido);
    Optional<Pedido> buscarPorId(Long id);
    void eliminarPorId(Long id);
    void editarPedido(Long id, Pedido pedidoActualizado);
    double validarDescuento(DetalleOrden detalleOrden);
    double validarSubtotal(double precio, int cantidad);
    double validarTotal(double total, double descuento);

}
