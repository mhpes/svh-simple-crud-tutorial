package es.mhp.dao;

import java.util.List;

/**
 * Created by Edu on 15/02/2016.
 */
public interface IAbstractDao<T> {
    List<T> findAll();
    T findById(int id);
    void create(T entity);
    T update(T entity);
    void delete(T entity);
    void save(T entity);
}
