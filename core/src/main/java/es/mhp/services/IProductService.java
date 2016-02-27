package es.mhp.services;

import es.mhp.entities.Product;
import es.mhp.services.dto.ProductDTO;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */
public interface IProductService {
    Set<ProductDTO> findAllProducts();
    Set<ProductDTO> findAllProducts(Product product);
    Set<ProductDTO> findAnyProduct(Product product);
    ProductDTO update(Product product);
    void delete(Product product);
    ProductDTO findProductById(long id);

}
