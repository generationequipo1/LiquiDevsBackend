package com.LDCream.Byte.proyectoEcommerceLiquidDevs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    @Column(unique = true) //se recomienda utilizar emails repetidas
    private String email;
    private String telefono;

    @JsonIgnore
    @Column(name = "password_hash", nullable = false)
    private String password_hash;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(nullable = false)
    private Boolean activo = true;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore // Evitar recursión infinita
    private List<Pedido> pedido;
    //
    public Usuario() {}
    // constructor con parametros
    public Usuario( String nombre, String apellido, String email, String telefono, String password_hash) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.password_hash = password_hash;
        this.fechaRegistro = LocalDateTime.now();
        this.activo = true;
    }
    // metodo de ciclo de vida y persistencia
    @PrePersist
    protected void onCreate(){
        if (fechaRegistro == null){
            fechaRegistro = LocalDateTime.now();
        }
        if (activo == null) {
            activo=true;
        }
    }
    //getter y setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public List<Pedido> getPedidos() {
        return pedido;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedido = pedidos;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


}
