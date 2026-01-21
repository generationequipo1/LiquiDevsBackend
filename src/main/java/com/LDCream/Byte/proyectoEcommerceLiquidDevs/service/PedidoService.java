package com.LDCream.Byte.proyectoEcommerceLiquidDevs.service;

import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.DetalleOrden;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.Pedido;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.Producto;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.repository.IdetalleOrdenRepository;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.repository.IpedidoRepository;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.repository.IproductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class PedidoService implements IpedidoService{

    private final IpedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(IpedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;

    }

    @Override
    public List<Pedido> buscarTodos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public Optional<Pedido> buscarPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id).orElseThrow();
        return Optional.of(pedido);
    }

    @Override
    public void eliminarPorId(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public void editarPedido(Long id, Pedido pedidoActualizado) {
       Pedido pedidoExistente = pedidoRepository.findById(id).orElse(null);

        if(pedidoExistente != null){
            pedidoActualizado.setUsuario(pedidoExistente.getUsuario());
            pedidoActualizado.setDetallesOrdenes(pedidoExistente.getDetallesOrdenes());
            pedidoActualizado.setDescuentos(pedidoExistente.getDescuentos());
            pedidoActualizado.setSubtotal(pedidoExistente.getSubtotal());
            pedidoActualizado.setValorTotal(pedidoExistente.getValorTotal());
        }
    }



    @Override
    public double validarDescuento(Pedido pedido) {
        double subtotal = pedido.getSubtotal();
        double descuento = 0.15;
        double valorDescuento = 0;

        if(subtotal >= 50000){
            valorDescuento =  subtotal * descuento;
        }
       return valorDescuento;
    }

    @Override
    public double validarSubtotal(Pedido pedido) {
        double subtotal = 0;
        int cantidad;
        BigDecimal precio;

        for (DetalleOrden iteradorDetalle : pedido.getDetallesOrdenes()){
                precio = iteradorDetalle.getProducto().getPrecio();
                cantidad = iteradorDetalle.getCantidad();
        }
        return subtotal;
    }

    @Override
    public double validarTotal(Pedido pedido) {
        return pedido.getSubtotal() - pedido.getDescuentos();
    }
}
