package org.jwt.repository.dao.impl;

import org.jwt.repository.dao.Dao;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public abstract class AbstractDao<T> implements Dao<T> {

    @Inject
    EntityManager em;

    private Class<T> clazz;

    public AbstractDao(final Class<T> clazz) {
        this.clazz = clazz;
    }

    public T find(final T entity) {
        return em.find(clazz, entity);
    }

    public List<T> findAll() {
        return em.createQuery("select t from " + clazz.getName(), clazz).getResultList();
    }

    public void create(final T entity){
        em.persist(entity);
    }

    public T update(final T entity){
        return em.merge( entity );
    }

    public void delete(final T entity){
        em.remove(entity);
    }
}
