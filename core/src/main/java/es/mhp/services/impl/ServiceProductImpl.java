package es.mhp.services.impl;

import es.mhp.entities.Category;
import es.mhp.entities.Product;
import es.mhp.repositories.CategoryRepository;
import es.mhp.repositories.ProductRepository;
import es.mhp.services.IProductService;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/*
* Created by Edu on 24/02/2016.
*/


@Service
@Transactional
@Configuration
@EnableJpaRepositories
public class ServiceProductImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Set<ProductDTO> findAllProducts() {
        Iterable<Product> addressSet = productRepository.findAll();

        Set<ProductDTO> productDTOs = new HashSet<>();

        for (Product product : addressSet){
            productDTOs.add(new ProductDTO(product));
        }

        return productDTOs;
    }

    @Override
    public Set<ProductDTO> findAnyProducts(String text) {
        Iterable<Product> productSet = productRepository.findByValue(text);

        Set<ProductDTO> productDTOs = new HashSet<>();

        for (Product currentProduct : productSet) {
            productDTOs.add(new ProductDTO(currentProduct));
        }

        return productDTOs;
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Product product = productRepository.findOne(productDTO.getProductId());
        Category category = categoryRepository.findOne(productDTO.getCategory());

        if (product != null){
            product.setCategory(category);
            productRepository.save(productDTO.toEntity(product));
        } else {
            product = new Product();
            product.setCategory(category);
            productRepository.save(productDTO.toEntity(product));
        }
        return new ProductDTO(product);
    }

    @Override
    public ProductDTO findById(String id) {
        return new ProductDTO(productRepository.findOne(id));
    }

    /*To Implement*/
    @Override
    public Set<AbstractDTO> findAll() {
        return null;
    }

    @Override
    public void delete(Object id) {
        productRepository.delete(id.toString());
    }
}
