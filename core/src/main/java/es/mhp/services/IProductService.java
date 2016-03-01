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
    Set<ProductDTO> findAnyProducts(Product product);
    ProductDTO save(ProductDTO productDTO);
    void delete(ProductDTO productDTO);
    ProductDTO findProductById(String id);
}
