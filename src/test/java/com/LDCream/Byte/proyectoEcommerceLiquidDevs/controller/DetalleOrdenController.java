package com.LDCream.Byte.proyectoEcommerceLiquidDevs.controller;

import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.DetalleOrden;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.Pedido;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.Producto;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.repository.DetalleOrdenRepository;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.repository.IproductoRepository;
import com.LDCream.Byte.proyectoEcommerceLiquidDevs.repository.IpedidoRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/detalles")
public class DetalleOrdenController {

    private final DetalleOrdenRepository detalleRepo;
    private final IpedidoRepository pedidoRepo;
    private final IproductoRepository productoRepo;

    public DetalleOrdenController(DetalleOrdenRepository detalleRepo,
                                  IpedidoRepository pedidoRepo,
                                  IproductoRepository productoRepo) {
        this.detalleRepo = detalleRepo;
        this.pedidoRepo = pedidoRepo;
        this.productoRepo = productoRepo;
    }

    // =========================
    // CREATE (POST)
    // =========================
    @PostMapping
    public ResponseEntity<DetalleOrden> crearDetalle(@Valid @RequestBody DetalleCreateRequest req) {

        Pedido pedido = pedidoRepo.findById(req.idPedido())
                .orElseThrow(() -> new RuntimeException("Pedido no existe: " + req.idPedido()));

        // CORRECCIÓN: no convertir a int
        Producto producto = productoRepo.findById(Math.toIntExact(req.idProducto()))
                .orElseThrow(() -> new RuntimeException("Producto no existe: " + req.idProducto()));

        DetalleOrden d = new DetalleOrden();
        d.setPedido(pedido);
        d.setProducto(producto);
        d.setPrecioUnitario(req.precioUnitario());
        d.setCantidad(req.cantidad());

        // subtotal = precio_unitario * cantidad
        d.setSubtotal(req.precioUnitario() * req.cantidad());

        DetalleOrden saved = detalleRepo.save(d);

        return ResponseEntity.created(URI.create("/api/detalles/" + saved.getIdDetalle()))
                .body(saved);
    }

    // =========================
    // READ (GET)
    // =========================
    @GetMapping
    public List<DetalleOrden> listar() {
        return detalleRepo.findAll();
    }

    @GetMapping("/{idDetalle}")
    public ResponseEntity<DetalleOrden> obtener(@PathVariable Long idDetalle) {
        return detalleRepo.findById(idDetalle)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/pedido/{idPedido}")
    public List<DetalleOrden> listarPorPedido(@PathVariable Long idPedido) {
        return detalleRepo.findByPedido_IdPedido(idPedido);
    }

    // =========================
    // UPDATE (PUT)
    // =========================
    @PutMapping("/{idDetalle}")
    public ResponseEntity<DetalleOrden> actualizar(@PathVariable Long idDetalle,@Valid @RequestBody DetalleUpdateRequest req) {

        return detalleRepo.findById(idDetalle)
                .map(d -> {
                    d.setPrecioUnitario(req.precioUnitario());
                    d.setCantidad(req.cantidad());
                    d.setSubtotal(req.precioUnitario() * req.cantidad());
                    return ResponseEntity.ok(detalleRepo.save(d));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // =========================
    // DELETE
    // =========================
    @DeleteMapping("/{idDetalle}")
    public ResponseEntity<Void> eliminar(@PathVariable Long idDetalle) {
        if (!detalleRepo.existsById(idDetalle)) {
            return ResponseEntity.notFound().build();
        }
        detalleRepo.deleteById(idDetalle);
        return ResponseEntity.noContent().build();
    }

    // =========================
    // DTOs (Request)
    // =========================
    public record DetalleCreateRequest(
            @NotNull Long idPedido,
            @NotNull Long idProducto,

            // CORRECCIÓN: DecimalMin para Double
            @NotNull @DecimalMin(value = "0.0", inclusive = true) Double precioUnitario,

            @NotNull @Min(1) Integer cantidad
    ) {}

    public record DetalleUpdateRequest(
            @NotNull @DecimalMin(value = "0.0", inclusive = true) Double precioUnitario,
            @NotNull @Min(1) Integer cantidad
    ) {}
}
