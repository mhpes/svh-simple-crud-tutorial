package es.mhp.services;

import es.mhp.entities.Product;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */
public interface IProductService {
    Set<Product> findAllProducts();
    Set<Product> findAllProducts(Product product);
    Set<Product> findAnyProduct(Product product);
    Product update(Product product);
    void delete(Product product);
    Product findProductById(long id);

}
