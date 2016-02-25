package es.mhp.dao.impl;

import es.mhp.entities.SellerContactInfo;
import es.mhp.dao.ISellerDao;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

/**
 * Created by Edu on 12/02/2016.
 */
@Component
public class SellerDaoImpl extends AbstractPetshopGenericDao<SellerContactInfo> implements ISellerDao {

    @Override
    public SellerContactInfo findById(long id) {
        return entityManager.find(SellerContactInfo.class, id);
    }

    @Override
    public List<SellerContactInfo> findAny(SellerContactInfo entity) {
        return findAll(entity, false);
    }

    @Override
    public List<SellerContactInfo> findAll(SellerContactInfo entity) {
        return findAll(entity, true);
    }

    @Override
    public List<SellerContactInfo> findAll() {
        Query query = entityManager.createQuery("SELECT a FROM SellerContactInfo a");
        return query.getResultList();
    }

    @Override
    protected List<SellerContactInfo> findAll(SellerContactInfo entity, boolean type) {
        String contatenator = type ? " AND ": " OR ";

        if (entity != null) {
            String queryParameters = "SELECT a FROM SellerContactInfo a WHERE ";

            if (!StringUtils.isEmpty(entity.getFirstName()) && (!StringUtils.isEmpty(entity.getLastName()))){
                queryParameters += "(FIRSTNAME = " + entity.getFirstName() + " AND " +
                                   "LASTNAME = "  + entity.getLastName() + ") " + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getEmail())) {
                queryParameters += "EMAIL = " + entity.getEmail() + contatenator;
            }

            queryParameters = replaceLast(queryParameters, contatenator, "");
            Query query = entityManager.createQuery(queryParameters);

            return (List<SellerContactInfo>) query.getResultList();
        }
        return Collections.emptyList();
    }
}
