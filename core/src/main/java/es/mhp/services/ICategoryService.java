package es.mhp.services;

import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.CategoryDTO;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */


public interface ICategoryService extends AbstractService{
    Set<AbstractDTO> findAnyCategories(String text);
    CategoryDTO save(CategoryDTO categoryDTO);
}
