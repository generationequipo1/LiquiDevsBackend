package com.LDCream.Byte.proyectoEcommerceLiquidDevs.controller;

import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.Producto;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.service.IproductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")

public class ProductoController {

    private final IproductoService productoService;

    @Autowired
    public ProductoController(IproductoService productoService) {

        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<?> getAllProductos() {
        try {
            List<Producto> productos = productoService.findAll();
            return ResponseEntity.ok(productos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(crearRespuestaError("Error al obtener productos: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductoById(@PathVariable Long id) {
        try {
            Optional<Producto> producto = productoService.findById(id);

            return producto.<ResponseEntity<?>>map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(crearRespuestaError("Producto no encontrado con ID: " + id)));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(crearRespuestaError("Error al obtener producto: " + e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<?> createProducto(@RequestBody Producto producto) {
        try {
            if (producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(crearRespuestaError("El nombre del producto es obligatorio"));
            }

            // Si tu precio es BigDecimal:
            BigDecimal precio = producto.getPrecio();
            if (precio == null || precio.compareTo(BigDecimal.ZERO) <= 0) {
                return ResponseEntity.badRequest()
                        .body(crearRespuestaError("El precio debe ser mayor a 0"));
            }

            Producto nuevoProducto = productoService.save(producto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(crearRespuestaError("Error al crear producto: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProducto(@PathVariable Long id, @RequestBody Producto producto) {
        try {
            Optional<Producto> productoExistente = productoService.findById(id);

            if (productoExistente.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(crearRespuestaError("Producto no encontrado con ID: " + id));
            }

            Producto productoActualizado = productoExistente.get();

            if (producto.getNombre() != null) productoActualizado.setNombre(producto.getNombre());
            if (producto.getDescripcion() != null) productoActualizado.setDescripcion(producto.getDescripcion());
            if (producto.getPrecio() != null) productoActualizado.setPrecio(producto.getPrecio());
            if (producto.getStock() != null) productoActualizado.setStock(producto.getStock());
            if (producto.getImagenUrl() != null) productoActualizado.setImagenUrl(producto.getImagenUrl());
            if (producto.getCategoria() != null) productoActualizado.setCategoria(producto.getCategoria());

            Producto productoGuardado = productoService.save(productoActualizado);
            return ResponseEntity.ok(productoGuardado);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(crearRespuestaError("Error al actualizar producto: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProducto(@PathVariable Long id) {
        try {
            Optional<Producto> producto = productoService.findById(id);

            if (producto.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(crearRespuestaError("Producto no encontrado con ID: " + id));
            }

            productoService.deleteById(id);

            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("mensaje", "Producto eliminado exitosamente");
            respuesta.put("id", id);

            return ResponseEntity.ok(respuesta);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(crearRespuestaError("Error al eliminar producto: " + e.getMessage()));
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> buscarProductos(@RequestParam String nombre) {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(crearRespuestaError("El parámetro 'nombre' es requerido"));
            }

            List<Producto> productos = productoService.findByNombreContaining(nombre);
            return ResponseEntity.ok(productos);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(crearRespuestaError("Error al buscar productos: " + e.getMessage()));
        }
    }

    private Map<String, Object> crearRespuestaError(String mensaje) {
        Map<String, Object> error = new HashMap<>();
        error.put("error", true);
        error.put("mensaje", mensaje);
        return error;
    }
}

    /*@GetMapping
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    @GetMapping("/{id}")
    public Producto obtenerProducto(@PathVariable Long id) {
        return productoService.buscarProductoPorId(id);

    @PostMapping
    public Producto guardarProducto(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }

    @DeleteMapping("/{id}")
    public void eliminarProducto(@PathVariable Long id) {
    productoService.eliminarProducto(id);   }
    }*/


