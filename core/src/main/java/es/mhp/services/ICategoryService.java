package es.mhp.services;

import es.mhp.entities.Category;

import java.util.List;

/**
 * Created by Edu on 24/02/2016.
 */


public interface ICategoryService {
    List<Category> findAllCategories();
    List<Category> findAllCategories(Category category);
    List<Category> findAnyCategorie(Category category);
    Category update(Category category);
    void delete(Category category);
    Category findCategoryById(long id);
}
