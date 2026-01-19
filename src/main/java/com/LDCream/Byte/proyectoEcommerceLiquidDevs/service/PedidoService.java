package com.LDCream.Byte.proyectoEcommerceLiquidDevs.service;

import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.DetalleOrden;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.Pedido;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.Producto;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.repository.IdetalleOrdenRepository;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.repository.IpedidoRepository;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.repository.IproductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
@Service
public class PedidoService implements IpedidoService{

    private final IpedidoRepository pedidoRepository;

    private final IdetalleOrdenRepository detalleOrdenRepository;

    @Autowired
    public PedidoService(IpedidoRepository pedidoRepository,  IdetalleOrdenRepository detalleOrdenRepository) {
        this.pedidoRepository = pedidoRepository;
        this.detalleOrdenRepository = detalleOrdenRepository;
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
    public double validarDescuento(DetalleOrden detalleOrden) {
        double porcentajeDescuento = 0.10;
        double precioProducto = detalleOrden.getProducto().getPrecio();
        int cantidadProducto = detalleOrden.getCantidad();
        double valorDescuento = 0;
         if(precioProducto * cantidadProducto > 50000){
            valorDescuento =  (precioProducto * cantidadProducto) * porcentajeDescuento;
         }
        return valorDescuento;
    }

    @Override
    public double validarSubtotal(double precio, int cantidad) {
        return 0;
    }

    @Override
    public double validarTotal(double total, double descuento) {
        return 0;
    }
}
