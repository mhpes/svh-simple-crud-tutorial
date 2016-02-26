package es.mhp.dao.impl;

import es.mhp.dao.IProductDao;
import es.mhp.entities.Product;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.Collections;
import java.util.Set;

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
    public Set<Product> findAny(Product entity) {
        return findAll(entity, false);
    }

    @Override
    public Set<Product> findAll(Product entity) {
        return findAll(entity, true);
    }

    @Override
    public Set<Product> findAll() {
        return (Set<Product>) entityManager.createQuery("SELECT a FROM Product a").getResultList();
    }

    @Override
    protected Set<Product> findAll(Product entity, boolean type) {
        String contatenator = type ? " AND ": " OR ";

        if (entity != null) {
            String queryParameters = "SELECT a FROM Product a WHERE ";

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

            return (Set<Product>) query.getResultList();
        }
        return Collections.emptySet();
    }
}
