package es.mhp.dao;

import es.mhp.entities.Item;

/**
 * Created by Edu on 12/02/2016.
 */
public interface IItemDao extends IPetshopGenericDao<Item>{
    Item findById(int id);
    void deleteById(int id);
}
