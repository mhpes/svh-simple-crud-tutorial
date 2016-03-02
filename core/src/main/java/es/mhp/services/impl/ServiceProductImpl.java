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

        Set<ProductDTO> productDTOs = new HashSet<>();

        for (Product product : addressSet){
            productDTOs.add(new ProductDTO(product));
        }

        return productDTOs;
    }

    @Override
    public Set<ProductDTO> findAllProducts(Product product) {
        Set<Product> productSet = iProductDao.findAll(product);

        Set<ProductDTO> productDTOs = new HashSet<>();

        for (Product currentProduct : productSet) {
            productDTOs.add(new ProductDTO(currentProduct));
        }

        return productDTOs;
    }

    @Override
    public Set<ProductDTO> findAnyProducts(Product product) {
        Set<Product> productSet = iProductDao.findAny(product);

        if (!productSet.isEmpty()){
            Set<ProductDTO> productDTOs = new HashSet<>();

            for (Product currentProduct : productSet) {
                productDTOs.add(new ProductDTO(currentProduct));
            }

            return productDTOs;
        }
        return Collections.emptySet();
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Product product = iProductDao.findById(productDTO.getProductId());

        if (product != null){
            iProductDao.update(productDTO.toEntity(product));
        } else {
            product = new Product();
            iProductDao.save(product);
        }
        return productDTO;
    }

    @Override
    public void delete(ProductDTO productDTO) {
        iProductDao.deleteById(productDTO.getProductId());
    }

    @Override
    public ProductDTO findProductById(String id) {
        return new ProductDTO(iProductDao.findById(id));
    }
}
