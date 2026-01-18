package com.LDCream.Byte.proyectoEcommerceLiquidDevs.model;
//temporal mientras david sube su parte

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;
    @CreationTimestamp
    @Column(name = "fecha_creacion",updatable = false, nullable = false)
    private LocalDateTime fechaCreacion;
    @Column(name = "descuento",nullable = false)
    private double descuentos;
    private double subtotal;
    private double valorTotal;


    @ManyToOne
    @JsonBackReference //Esta anotacion va en la entidad que recibe
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany
    private List<DetalleOrden> detallesOrdenes;

    public Pedido(double descuentos, double subtotal, double valorTotal) {

        this.descuentos = descuentos;
        this.subtotal = subtotal;
        this.valorTotal = valorTotal;
    }
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Long idPedido) {
        this.idPedido = idPedido;
    }
    public double getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(double descuentos) {
        this.descuentos = descuentos;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetalleOrden> getDetallesOrdenes() {
        return detallesOrdenes;
    }

    public void setDetallesOrdenes(List<DetalleOrden> detallesOrdenes) {
        this.detallesOrdenes = detallesOrdenes;
    }
}
