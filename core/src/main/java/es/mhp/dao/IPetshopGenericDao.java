package es.mhp.dao;

import entities.AbstractEntity;
import entities.Address;

import java.util.List;

/**
 * Created by Edu on 15/02/2016.
 */
public interface IPetshopGenericDao<T extends AbstractEntity> {
    T update(T entity);
    void delete(T entity);
    void save(T entity);
    T findById(long id);
    List<T> findAll();
    List<T> findAny(Address address);
    List<T> findAll(Address address);
}
