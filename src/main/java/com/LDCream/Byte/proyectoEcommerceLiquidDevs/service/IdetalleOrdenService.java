package com.LDCream.Byte.proyectoEcommerceLiquidDevs.service;

import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.DetalleOrden;

import java.util.List;
import java.util.Optional;

public interface IdetalleOrdenService {
    List<DetalleOrden> detalleOrdenes();
    Optional<DetalleOrden> buscarPorId(Long id);
    DetalleOrden guardarDetalleOrden(DetalleOrden detalleOrden);
}
