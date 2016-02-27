/*
package es.mhp.services.impl;

import es.mhp.dao.IProductDao;
import es.mhp.entities.Product;
import es.mhp.services.IProductService;
import es.mhp.services.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

*/
/**
 * Created by Edu on 24/02/2016.
 *//*

@Service
public class ServiceProductImpl implements IProductService {

    @Autowired
    private IProductDao iProductDao;


    public Set<ProductDTO> findAllProducts() {
        return iProductDao.findAll();
    }

    public Set<ProductDTO> findAllProducts(Product product) {
        return iProductDao.findAll(product);
    }

    public Set<ProductDTO> findAnyProduct(Product product) {
        return iProductDao.findAny(product);
    }

    public ProductDTO update(Product product) {
        return iProductDao.update(product);
    }

    public void delete(Product product) {
        iProductDao.delete(product);
    }

    public ProductDTO findProductById(long id) {
        return iProductDao.findById(id);
    }
}
*/
