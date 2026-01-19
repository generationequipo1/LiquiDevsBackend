package com.LDCream.Byte.proyectoEcommerceLiquidDevs.service;

import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.DetalleOrden;

import java.util.List;
import java.util.Optional;

public class DetalleOrdenService implements IdetalleOrdenService{
    @Override
    public List<DetalleOrden> detalleOrdenes() {
        return List.of();
    }

    @Override
    public Optional<DetalleOrden> buscarPorId(Long id) {
        return Optional.empty();
    }

    @Override
    public DetalleOrden guardarDetalleOrden(DetalleOrden detalleOrden) {
        return null;
    }
}
