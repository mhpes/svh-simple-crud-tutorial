package es.mhp.services.impl;

import es.mhp.entities.Product;
import es.mhp.dao.IProductDao;
import es.mhp.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Edu on 24/02/2016.
 */
@Service
public class ServiceProductImpl implements IProductService {

    @Autowired
    private IProductDao iProductDao;


    public List<Product> findAllProducts() {
        return iProductDao.findAll();
    }

    public List<Product> findAllProducts(Product product) {
        return iProductDao.findAll(product);
    }

    public List<Product> findAnyProduct(Product product) {
        return iProductDao.findAny(product);
    }

    public Product update(Product product) {
        return iProductDao.update(product);
    }

    public void delete(Product product) {
        iProductDao.delete(product);
    }

    public Product findProductById(long id) {
        return iProductDao.findById(id);
    }
}
