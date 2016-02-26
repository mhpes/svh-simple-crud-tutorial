package es.mhp.services;

import es.mhp.entities.Category;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */


public interface ICategoryService {
    Set<Category> findAllCategories();
    Set<Category> findAllCategories(Category category);
    Set<Category> findAnyCategorie(Category category);
    Category update(Category category);
    void delete(Category category);
    Category findCategoryById(long id);
}
