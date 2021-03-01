package org.jwt.repository.dao;

import java.util.List;

public interface Dao<T> {

    T find(final T entity);

    List<T> findAll();

    void create(T entity);

    T update(T entity);

    void delete(T entity);

}
