package es.mhp.dao.impl;

import entities.Product;
import es.mhp.dao.IProductDao;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

/**
 * Created by Edu on 12/02/2016.
 */
@Component
public class ProductDaoImpl extends AbstractPetshopGenericDao<Product> implements IProductDao {

    @Override
    public Product findById(long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public List<Product> findAny(Product entity) {
        return findAll(entity, false);
    }

    @Override
    public List<Product> findAll(Product entity) {
        return findAll(entity, true);
    }

    @Override
    public List<Product> findAll() {
        Query query = entityManager.createQuery("SELECT a FROM PRODUCT a");
        return query.getResultList();
    }

    @Override
    protected List<Product> findAll(Product entity, boolean type) {
        String contatenator = type ? " AND ": " OR ";

        if (entity != null) {
            String queryParameters = "SELECT a FROM PRODUCT a WHERE ";

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

            return (List<Product>) query.getResultList();
        }
        return Collections.emptyList();
    }
}
