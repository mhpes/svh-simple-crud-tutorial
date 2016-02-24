package es.mhp.services.impl;

import entities.Category;
import es.mhp.dao.ICategoryDao;
import es.mhp.services.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Edu on 24/02/2016.
 */
@Service
public class ServiceCategoryImpl implements ICategoryService {

    @Autowired
    private ICategoryDao iCategoryDao;

    public List<Category> findAllCategories() {
        return iCategoryDao.findAll();
    }

    public List<Category> findAllCategories(Category category) {
        return iCategoryDao.findAll(category);
    }

    public List<Category> findAnyCategorie(Category category) {
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
