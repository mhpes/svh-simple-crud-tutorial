package es.mhp.services.impl;

import es.mhp.entities.Category;
import es.mhp.repositories.CategoryRepository;
import es.mhp.services.ICategoryService;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */
@Service
@Transactional
@Configuration
@EnableJpaRepositories
public class ServiceCategoryImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Set<AbstractDTO> findAnyCategories(String text) {
        Iterable<Category> categorySet = categoryRepository.findByValue(text);

        Set<AbstractDTO> categoryDTOs = new HashSet<>();

        for (Category currentCategory : categorySet) {
            categoryDTOs.add(new CategoryDTO(currentCategory));
        }

        return categoryDTOs;
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        return new CategoryDTO(categoryRepository.save(categoryDTO.toEntity()));
    }

    @Override
    public Set<AbstractDTO> findAll() {
        Iterable<Category> categories = categoryRepository.findAll();

        Set<AbstractDTO> categoryDTOs = new HashSet<>();

        for (Category category : categories){
            categoryDTOs.add(new CategoryDTO(category));
        }

        return categoryDTOs;
    }

    @Override
    public void delete(Object id) {
        categoryRepository.delete(id.toString());
    }
}
