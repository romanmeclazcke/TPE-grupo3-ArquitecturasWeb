package com.example.entregable3.Services;

import java.util.List;

public interface BaseServices<E>{

    public List<E> findAll() throws Exception;

    public E findById(Integer id) throws Exception;

    public E save(E entity) throws Exception;

    public E update(Integer id, E entity) throws Exception;

    public boolean delete(Integer id) throws Exception;
}
