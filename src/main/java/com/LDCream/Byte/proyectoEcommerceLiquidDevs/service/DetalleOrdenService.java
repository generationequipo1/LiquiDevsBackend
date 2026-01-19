package com.LDCream.Byte.proyectoEcommerceLiquidDevs.service;

import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.DetalleOrden;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.repository.IdetalleOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleOrdenService implements IdetalleOrdenService{

    private final IdetalleOrdenRepository detalleOrdenRepository;

    @Autowired
    public DetalleOrdenService(IdetalleOrdenRepository detalleOrdenRepository) {
        this.detalleOrdenRepository = detalleOrdenRepository;
    }

    @Override
    public List<DetalleOrden> detalleOrdenes() {
        return this.detalleOrdenRepository.findAll();
    }

    @Override
    public Optional<DetalleOrden> buscarPorId(Long id) {
        DetalleOrden detalleOrden = detalleOrdenRepository.findById(id).orElseThrow(null);
        return Optional.of(detalleOrden);
    }

    @Override
    public DetalleOrden guardarDetalleOrden(DetalleOrden detalleOrden) {
        return detalleOrdenRepository.save(detalleOrden);
    }
}
