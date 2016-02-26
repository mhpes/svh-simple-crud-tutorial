package es.mhp.services.impl;

import es.mhp.dao.ICategoryDao;
import es.mhp.entities.Category;
import es.mhp.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */
@Service
public class ServiceCategoryImpl implements ICategoryService {

    @Autowired
    private ICategoryDao iCategoryDao;

    public Set<Category> findAllCategories() {
        return iCategoryDao.findAll();
    }

    public Set<Category> findAllCategories(Category category) {
        return iCategoryDao.findAll(category);
    }

    public Set<Category> findAnyCategorie(Category category) {
        return iCategoryDao.findAny(category);
    }

    public Category update(Category category) {
        return iCategoryDao.update(category);
    }

    public void delete(Category category) {
        iCategoryDao.delete(category);
    }

    public Category findCategoryById(long id) {
        return iCategoryDao.findById(id);
    }
}
