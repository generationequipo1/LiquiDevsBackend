package com.LDCream.Byte.proyectoEcommerceLiquidDevs.service;

import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.Producto;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.repository.IproductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService implements IproductoService {

    private final IproductoRepository productoRepository;

    @Autowired
    public ProductoService(IproductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public Producto save(Producto producto) {
        // Si es nuevo producto, establecer fecha de creación y estado
        if (producto.getIdProducto() == null) {
            producto.setFechaCreacion(LocalDateTime.now());
            producto.setEstado(true);
        }
        producto.setFechaCreacion(LocalDateTime.now());

        return productoRepository.save(producto);
    }

    @Override
    public void deleteById(Long id) {

        productoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findByNombreContaining(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }
}


/*package com.LDCream.Byte.proyectoEcommerceLiquidDevs.service;

import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.Producto;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.repository.IproductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductoService implements IproductoService {

    private final IproductoRepository productoRepository;

    @Autowired
    public ProductoService(IproductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }


    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Producto buscarProductoPorId(Integer id) {
        return null;
    }


    @Override
    public Producto buscarProductoPorId(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        producto.setFechaCreacion(LocalDateTime.now());
        producto.setEstado(true);
        return productoRepository.save(producto);
    }

    @Override
    public void eliminarProducto(Integer id) {

    }

    @Override
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public Producto actualizarStock(Integer id, Integer cantidad) {
        return null;
    }
}*/