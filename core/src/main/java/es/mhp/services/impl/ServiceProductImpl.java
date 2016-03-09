package es.mhp.services.impl;

import es.mhp.dao.ICategoryDao;
import es.mhp.dao.IProductDao;
import es.mhp.entities.Category;
import es.mhp.entities.Product;
import es.mhp.services.IProductService;
import es.mhp.services.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/*
* Created by Edu on 24/02/2016.
*/


@Service
@Transactional
public class ServiceProductImpl implements IProductService {

    @Autowired
    private IProductDao iProductDao;

    @Autowired
    private ICategoryDao iCategoryDao;

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
    public Set<ProductDTO> findAllProducts(ProductDTO productDTO) {
        Set<Product> productSet = iProductDao.findAll(productDTO.toEntity(new Product()));

        Set<ProductDTO> productDTOs = new HashSet<>();

        for (Product currentProduct : productSet) {
            productDTOs.add(new ProductDTO(currentProduct));
        }

        return productDTOs;
    }

    @Override
    public Set<ProductDTO> findAnyProducts(ProductDTO productDTO) {
        Set<Product> productSet = iProductDao.findAny(productDTO.toEntity(new Product()));

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
    public Set<ProductDTO> findAnyProducts(String text) {
        Set<Product> productSet = iProductDao.findAny(text);

        Set<ProductDTO> productDTOs = new HashSet<>();

        for (Product currentProduct : productSet) {
            productDTOs.add(new ProductDTO(currentProduct));
        }

        return productDTOs;
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Product product = iProductDao.findById(productDTO.getProductId());
        Category category = iCategoryDao.findById(productDTO.getCategory());

        if (product != null){
            product.setCategory(category);
            iProductDao.update(productDTO.toEntity(product));
        } else {
            product = new Product();
            product.setCategory(category);
            iProductDao.save(productDTO.toEntity(product));
        }
        return new ProductDTO(product);
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
