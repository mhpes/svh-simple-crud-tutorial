package es.mhp.dao.impl;

import entities.Ziplocation;
import es.mhp.dao.IZiplocationDao;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

/**
 * Created by Edu on 12/02/2016.
 */
@Component
public class ZiplocationDaoImpl extends AbstractPetshopGenericDao<Ziplocation> implements IZiplocationDao {

    public Ziplocation findById(long id) {
        return entityManager.find(Ziplocation.class, id);
    }

    public List<Ziplocation> findAny(Ziplocation entity) {
        return findAll(entity, false);
    }

    public List<Ziplocation> findAll(Ziplocation entity) {
        return findAll(entity, true);
    }

    public List<Ziplocation> findAll() {
        Query query = entityManager.createQuery("SELECT a FROM ZIPLOCATION a");
        return query.getResultList();
    }

    @Override
    protected List<Ziplocation> findAll(Ziplocation entity, boolean type) {
        String contatenator = type ? " AND ": " OR ";

        if (entity != null) {
            String queryParameters = "SELECT a FROM ZIPLOCATION a WHERE ";

            if (!StringUtils.isEmpty(entity.getCity())) {
                queryParameters += "CITY = " + entity.getCity() + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getState())) {
                queryParameters += "STATE = " + entity.getState() + contatenator;
            }

            queryParameters = replaceLast(queryParameters, contatenator, "");
            Query query = entityManager.createQuery(queryParameters);

            return (List<Ziplocation>) query.getResultList();
        }
        return Collections.emptyList();
    }
}



