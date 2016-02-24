package es.mhp.services;

import entities.Product;

import java.util.List;

/**
 * Created by Edu on 24/02/2016.
 */
public interface IProductService {
    List<Product> findAllProducts();
    List<Product> findAllProducts(Product product);
    List<Product> findAnyProduct(Product product);
    Product update(Product product);
    void delete(Product product);
    Product findProductById(long id);

}
