package es.mhp.services.impl;

import es.mhp.dao.IProductDao;
import es.mhp.entities.Product;
import es.mhp.services.IProductService;
import es.mhp.services.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*
* Created by Edu on 24/02/2016.
*/


@Service
public class ServiceProductImpl implements IProductService {

    @Autowired
    private IProductDao iProductDao;

    @Override
    public Set<ProductDTO> findAllProducts() {
        Set<Product> addressSet = iProductDao.findAll();

        Set<ProductDTO> ProductDTOs = new HashSet<>();

        for (Product product : addressSet){
            ProductDTOs.add(new ProductDTO(product));
        }

        return ProductDTOs;
    }

    @Override
    public Set<ProductDTO> findAllProducts(Product product) {
        Set<Product> productSet = iProductDao.findAll(product);

        Set<ProductDTO> ProductDTOs = new HashSet<>();

        for (Product currentProduct : productSet) {
            ProductDTOs.add(new ProductDTO(currentProduct));
        }

        return ProductDTOs;
    }

    @Override
    public Set<ProductDTO> findAnyProducts(Product product) {
        Set<Product> productSet = iProductDao.findAny(product);

        if (!productSet.isEmpty()){
            Set<ProductDTO> ProductDTOs = new HashSet<>();

            for (Product currentProduct : productSet) {
                ProductDTOs.add(new ProductDTO(currentProduct));
            }

            return ProductDTOs;
        }
        return Collections.emptySet();
    }

    @Override
    public ProductDTO save(ProductDTO ProductDTO) {
        Product product = iProductDao.findById(ProductDTO.getProductId());

        if (product != null){
            iProductDao.update(ProductDTO.ToEntity(product));
        } else {
            product = new Product();
            iProductDao.save(product);
        }
        return ProductDTO;
    }

    @Override
    public void delete(ProductDTO ProductDTO) {
        iProductDao.deleteById(ProductDTO.getProductId());
    }

    @Override
    public ProductDTO findProductById(String id) {
        return new ProductDTO(iProductDao.findById(id));
    }
}
