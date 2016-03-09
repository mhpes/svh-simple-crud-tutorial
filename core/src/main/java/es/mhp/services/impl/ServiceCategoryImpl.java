package es.mhp.services.impl;

import es.mhp.dao.ICategoryDao;
import es.mhp.entities.Category;
import es.mhp.services.ICategoryService;
import es.mhp.services.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */
@Service
@Transactional
public class ServiceCategoryImpl implements ICategoryService {

    @Autowired
    private ICategoryDao iCategoryDao;

    @Override
    public Set<CategoryDTO> findAllCategories() {
        Set<Category> categorySet = iCategoryDao.findAll();

        Set<CategoryDTO> categoryDTOs = new HashSet<>();

        for (Category category : categorySet){
            categoryDTOs.add(new CategoryDTO(category));
        }

        return categoryDTOs;
    }

    @Override
    public Set<CategoryDTO> findAllCategories(CategoryDTO categoryDTO) {
        Set<Category> categorySet = iCategoryDao.findAll(categoryDTO.toEntity(new Category()));

        Set<CategoryDTO> categoryDTOs = new HashSet<>();

        for (Category currentCategory : categorySet){
            categoryDTOs.add(new CategoryDTO(currentCategory));
        }

        return categoryDTOs;
    }

    @Override
    public Set<CategoryDTO> findAnyCategories(CategoryDTO categoryDTO) {
        Set<Category> categorySet = iCategoryDao.findAny(categoryDTO.toEntity(new Category()));

        Set<CategoryDTO> categoryDTOs = new HashSet<>();

        for (Category currentCategory : categorySet){
            categoryDTOs.add(new CategoryDTO(currentCategory));
        }

        return categoryDTOs;
    }

    @Override
    public Set<CategoryDTO> findAnyCategories(String text) {
        Set<Category> categorySet = iCategoryDao.findAny(text);

        Set<CategoryDTO> categoryDTOs = new HashSet<>();

        for (Category currentCategory : categorySet) {
            categoryDTOs.add(new CategoryDTO(currentCategory));
        }

        return categoryDTOs;
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        Category category = iCategoryDao.findById(categoryDTO.getCategoryId());

        if (category != null){
            iCategoryDao.update(categoryDTO.toEntity(category));
        } else {
            category = new Category();
            iCategoryDao.save(categoryDTO.toEntity(category));
        }
        return new CategoryDTO(category);
    }

    @Override
    public void delete(CategoryDTO categoryDTO) {
        iCategoryDao.deleteById(categoryDTO.getCategoryId());
    }

    @Override
    public CategoryDTO findCategoryById(String id) {
        return new CategoryDTO(iCategoryDao.findById(id));
    }
}
