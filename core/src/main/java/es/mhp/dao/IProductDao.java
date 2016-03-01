package es.mhp.dao;

import es.mhp.entities.Product;

/**
 * Created by Edu on 12/02/2016.
 */
public interface IProductDao extends IPetshopGenericDao<Product>{
    void deleteById(String id);
    Product findById(String id);
}
