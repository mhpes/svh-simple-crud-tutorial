package es.mhp.dao.impl;

import es.mhp.entities.ZipLocation;
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
public class ZiplocationDaoImpl extends AbstractPetshopGenericDao<ZipLocation> implements IZiplocationDao {

    @Override
    public ZipLocation findById(long id) {
        return entityManager.find(ZipLocation.class, id);
    }

    @Override
    public List<ZipLocation> findAny(ZipLocation entity) {
        return findAll(entity, false);
    }

    @Override
    public List<ZipLocation> findAll(ZipLocation entity) {
        return findAll(entity, true);
    }

    @Override
    public List<ZipLocation> findAll() {
        Query query = entityManager.createQuery("SELECT a FROM ZipLocation a");
        return query.getResultList();
    }

    @Override
    protected List<ZipLocation> findAll(ZipLocation entity, boolean type) {
        String contatenator = type ? " AND ": " OR ";

        if (entity != null) {
            String queryParameters = "SELECT a FROM ZipLocation a WHERE ";

            if (!StringUtils.isEmpty(entity.getCity())) {
                queryParameters += "CITY = " + entity.getCity() + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getState())) {
                queryParameters += "STATE = " + entity.getState() + contatenator;
            }

            queryParameters = replaceLast(queryParameters, contatenator, "");
            Query query = entityManager.createQuery(queryParameters);

            return (List<ZipLocation>) query.getResultList();
        }
        return Collections.emptyList();
    }
}



