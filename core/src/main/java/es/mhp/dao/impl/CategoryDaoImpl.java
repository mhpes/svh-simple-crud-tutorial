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
        return entityManager.find(Category.class, id);
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
    public Set<Category> findAll() {
        return new HashSet<>(entityManager.createQuery("SELECT a FROM Category a").getResultList());
    }

    @Override
    protected Set<Category> findAll(Category entity, boolean type) {
        String contatenator = type ? " AND ": " OR ";

        if (entity != null) {
            String queryParameters = "SELECT a FROM Category a WHERE ";

            if (!StringUtils.isEmpty(entity.getName())) {
                queryParameters += "NAME = " + entity.getName() + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getDescription())) {
                queryParameters += "DESCRIPTION = " + entity.getDescription() + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getImageUrl())) {
                queryParameters += "IMAGEURL = " + entity.getImageUrl() + contatenator;
            }

            queryParameters = replaceLast(queryParameters, contatenator, "");
            Query query = entityManager.createQuery(queryParameters);

            return (Set<Category>) query.getResultList();
        }
        return Collections.emptySet();
    }

    private List<Category> findAllMocked() {
        List<Category> addresses = new ArrayList<>();

        addresses.add(new Category("http://www.dogsaffaire.com/blog/wp-content/uploads/2016/02/perro-listo.jpg", "Perro Listo", "Paco", "1"));
        addresses.add(new Category("http://img.desmotivaciones.es/201110/perrofeo1.jpg", "Perro Feo", "Haineto", "1"));

        return addresses;
    }
}
