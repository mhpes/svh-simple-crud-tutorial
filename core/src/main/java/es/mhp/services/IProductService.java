package es.mhp.services;

import es.mhp.services.dto.ProductDTO;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */
public interface IProductService {
    Set<ProductDTO> findAllProducts();
    Set<ProductDTO> findAllProducts(ProductDTO productDTO);
    Set<ProductDTO> findAnyProducts(ProductDTO productDTO);
    Set<ProductDTO> findAnyProducts(String text);
    ProductDTO save(ProductDTO productDTO);
    void delete(ProductDTO productDTO);
    ProductDTO findProductById(String id);
}
