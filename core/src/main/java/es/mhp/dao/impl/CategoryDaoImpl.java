package es.mhp.dao.impl;

import es.mhp.dao.ICategoryDao;
import es.mhp.entities.Category;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.*;

/**
 * Created by Edu on 12/02/2016.
 */
@Component
@Transactional
public class CategoryDaoImpl extends AbstractPetshopGenericDao<Category> implements ICategoryDao{

    @Override
    public Category findById(String id) {
        return getEntityManager().find(Category.class, id);
    }

    @Override
    public void deleteById(String id) {
        Category category = findById(id);
        if (category != null) getEntityManager().remove(category);
    }

    @Override
    public Set<Category> findAny(Category entity) {
        return findAll(entity, false);
    }

    @Override
    public Set<Category> findAll(Category entity) {
        return findAll(entity, true);
    }

    @Override
    public Set<Category> findAny(String text) {
        String concatenator = " OR ";

        if (text != null) {
            String queryParameters = "SELECT a FROM Category a WHERE ";

            queryParameters += "CATEGORYID like '%" + text + "%' " + concatenator;
            queryParameters += "NAME like '%" + text + "%' " + concatenator;
            queryParameters += "DESCRIPTION like '%" + text + "%' " +  concatenator;
            queryParameters += "IMAGEURL like '%" + text + "%' ";

            return new HashSet (getEntityManager().createQuery(queryParameters).getResultList());
        }
        return Collections.emptySet();
    }

    @Override
    public Set<Category> findAll() {
        return new HashSet<>(getEntityManager().createQuery("SELECT a FROM Category a").getResultList());
    }

    @Override
    protected Set<Category> findAll(Category entity, boolean type) {
        String concatenator = type ? " AND " : " OR ";

        if (entity != null) {
            String queryParameters = "SELECT a FROM Category a WHERE ";

            if (!StringUtils.isEmpty(entity.getName())) {
                queryParameters += "NAME = " + entity.getName() + concatenator;
            }

            if (!StringUtils.isEmpty(entity.getDescription())) {
                queryParameters += "DESCRIPTION = " + entity.getDescription() + concatenator;
            }

            if (!StringUtils.isEmpty(entity.getImageUrl())) {
                queryParameters += "IMAGEURL = " + entity.getImageUrl() + concatenator;
            }

            queryParameters = replaceLast(queryParameters, concatenator, "");
            Query query = getEntityManager().createQuery(queryParameters);

            return (Set<Category>) query.getResultList();
        }
        return Collections.emptySet();
    }
}
