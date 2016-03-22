package es.mhp.services;

import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.ProductDTO;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */
public interface IProductService extends AbstractService {
    Set<AbstractDTO> findAnyProducts(String text);
    ProductDTO save(ProductDTO productDTO);
}
