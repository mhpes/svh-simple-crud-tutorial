package es.mhp.dao.impl;

import es.mhp.dao.IZiplocationDao;
import es.mhp.entities.ZipLocation;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
    public Set<ZipLocation> findAny(ZipLocation entity) {
        return findAll(entity, false);
    }

    @Override
    public Set<ZipLocation> findAll(ZipLocation entity) {
        return findAll(entity, true);
    }

    @Override
    public Set<ZipLocation> findAll() {
        return new HashSet<> (entityManager.createQuery("SELECT a FROM ZipLocation a").getResultList());
    }

    @Override
    protected Set<ZipLocation> findAll(ZipLocation entity, boolean type) {
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

            return (Set<ZipLocation>) query.getResultList();
        }
        return Collections.emptySet();
    }

    @Override
    public void deleteById(long id) {
        ZipLocation zipLocation = getEntityManager().find(ZipLocation.class, id);
        getEntityManager().remove(zipLocation);
    }
}



