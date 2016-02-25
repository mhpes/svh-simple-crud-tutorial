package es.mhp.dao.impl;

import entities.Category;
import es.mhp.dao.ICategoryDao;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Edu on 12/02/2016.
 */
@Component
public class CategoryDaoImpl extends AbstractPetshopGenericDao<Category> implements ICategoryDao{

    @Override
    public Category findById(long id) {
        return entityManager.find(Category.class, id);
    }

    @Override
    public List<Category> findAny(Category entity) {
        return findAll(entity, false);
    }

    @Override
    public List<Category> findAll(Category entity) {
        return findAll(entity, true);
    }

    @Override
    public List<Category> findAll() {
        /*Query query = entityManager.createQuery("SELECT a FROM CATEGORY a");
        return query.getResultList();*/
        return findAllMocked();
    }

    @Override
    protected List<Category> findAll(Category entity, boolean type) {
        String contatenator = type ? " AND ": " OR ";

        if (entity != null) {
            String queryParameters = "SELECT a FROM CATEGORY a WHERE ";

            if (!StringUtils.isEmpty(entity.getName())) {
                queryParameters += "NAME = " + entity.getName() + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getDescription())) {
                queryParameters += "DESCRIPTION = " + entity.getDescription() + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getImageUrl())) {
                queryParameters += "IMAGE_URL = " + entity.getImageUrl() + contatenator;
            }

            queryParameters = replaceLast(queryParameters, contatenator, "");
            Query query = entityManager.createQuery(queryParameters);

            return (List<Category>) query.getResultList();
        }
        return Collections.emptyList();
    }

    private List<Category> findAllMocked() {
        List<Category> addresses = new ArrayList<>();

        addresses.add(new Category("http://www.dogsaffaire.com/blog/wp-content/uploads/2016/02/perro-listo.jpg", "Perro Listo", "Paco", "1"));
        addresses.add(new Category("http://img.desmotivaciones.es/201110/perrofeo1.jpg", "Perro Feo", "Haineto", "1"));

        return addresses;
    }
}
