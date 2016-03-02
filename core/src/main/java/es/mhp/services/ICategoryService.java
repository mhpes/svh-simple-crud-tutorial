package es.mhp.services;

import es.mhp.entities.Category;
import es.mhp.services.dto.CategoryDTO;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */


public interface ICategoryService {
    Set<CategoryDTO> findAllCategories();
    Set<CategoryDTO> findAllCategories(Category category);
    Set<CategoryDTO> findAnyCategories(Category category);
    CategoryDTO save(CategoryDTO categoryDTO);
    void delete(CategoryDTO categoryDTO);
    CategoryDTO findCategoryById(String id);
}
