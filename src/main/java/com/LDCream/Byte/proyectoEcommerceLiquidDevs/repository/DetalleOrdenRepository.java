package com.LDCream.Byte.proyectoEcommerceLiquidDevs.repository;

import com.LDCream.Byte.proyectoEcommerceLiquidDevs.model.DetalleOrden;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class DetalleOrdenRepository implements JpaRepository<DetalleOrden, Long> {
    public List<DetalleOrden> findByPedido_IdPedido(Long idPedido) {
        return null;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends DetalleOrden> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends DetalleOrden> List<S> saveAllAndFlush(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public void deleteAllInBatch(Iterable<DetalleOrden> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public DetalleOrden getOne(Long aLong) {
        return null;
    }

    @Override
    public DetalleOrden getById(Long aLong) {
        return null;
    }

    @Override
    public DetalleOrden getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends DetalleOrden> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends DetalleOrden> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends DetalleOrden> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends DetalleOrden> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends DetalleOrden> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends DetalleOrden> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends DetalleOrden, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends DetalleOrden> S save(S entity) {
        return null;
    }

    @Override
    public <S extends DetalleOrden> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<DetalleOrden> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<DetalleOrden> findAll() {
        return List.of();
    }

    @Override
    public List<DetalleOrden> findAllById(Iterable<Long> longs) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(DetalleOrden entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends DetalleOrden> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<DetalleOrden> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<DetalleOrden> findAll(Pageable pageable) {
        return null;
    }
}
