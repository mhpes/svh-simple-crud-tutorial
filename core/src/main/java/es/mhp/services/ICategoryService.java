package es.mhp.services;

import es.mhp.services.dto.CategoryDTO;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */


public interface ICategoryService {
    Set<CategoryDTO> findAllCategories();
    Set<CategoryDTO> findAnyCategories(String text);
    CategoryDTO save(CategoryDTO categoryDTO);
    void delete(CategoryDTO categoryDTO);
}
