package es.mhp.dao.impl;

import entities.SellerContactInfo;
import es.mhp.dao.ISellerDao;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

/**
 * Created by Edu on 12/02/2016.
 */
public class SellerDaoImpl extends AbstractPetshopGenericDao<SellerContactInfo> implements ISellerDao {

    public SellerContactInfo findById(long id) {
        return entityManager.find(SellerContactInfo.class, id);
    }

    public List<SellerContactInfo> findAny(SellerContactInfo entity) {
        return findAll(entity, false);
    }

    public List<SellerContactInfo> findAll(SellerContactInfo entity) {
        return findAll(entity, true);
    }

    public List<SellerContactInfo> findAll() {
        Query query = entityManager.createQuery("SELECT a FROM SELLER_CONTACT_INFO a");
        return query.getResultList();
    }

    @Override
    protected List<SellerContactInfo> findAll(SellerContactInfo entity, boolean type) {
        String contatenator = type ? " AND ": " OR ";

        if (entity != null) {
            String queryParameters = "SELECT a FROM SELLER_CONTACT_INFO a WHERE ";

            if (!StringUtils.isEmpty(entity.getFirstName()) && (!StringUtils.isEmpty(entity.getLastName()))){
                queryParameters += "(FIRST_NAME = " + entity.getFirstName() + " AND " +
                                   "LAST_NAME = "  + entity.getLastName() + ") " + contatenator;
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
