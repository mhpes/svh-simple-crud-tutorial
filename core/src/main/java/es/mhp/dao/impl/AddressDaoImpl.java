package es.mhp.dao.impl;


import entities.Address;
import es.mhp.dao.IAddressDao;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;

/**
 * Created by Edu on 12/02/2016.
 */

public class AddressDaoImpl extends AbstractPetshopGenericDao<Address> implements IAddressDao {

    public Address findById(long id) {
        return entityManager.find(Address.class, id);
    }

    public List<Address> findAny(Address entity) {
        return findAll(entity, false);
    }

    public List<Address> findAll(Address entity) {
        return findAll(entity, true);
    }

    public List<Address> findAll() {
        Query query = entityManager.createQuery("SELECT a FROM ADDRESS a");
        return query.getResultList();
    }

    @Override
    protected List<Address> findAll(Address entity, boolean type) {
        String contatenator = type ? " AND ": " OR ";

        if (entity != null) {
            String queryParameters = "SELECT a FROM ADDRESS a WHERE ";

            if (!StringUtils.isEmpty(entity.getMainStreet())) {
                queryParameters += "STREET1 = " + entity.getMainStreet() + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getSecondaryStreet())) {
                queryParameters += "STREET2 = " + entity.getSecondaryStreet() + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getCity())) {
                queryParameters += "CITY = " + entity.getCity() + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getState())) {
                queryParameters += "STATE = " + entity.getState() + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getLatitude()) && !StringUtils.isEmpty(entity.getLongitude())) {
                queryParameters += "LATITUDE = " + entity.getLatitude() +
                                  "AND LONGITUDE = " + entity.getLongitude() + contatenator;
            }

            queryParameters = replaceLast(queryParameters, contatenator, "");
            Query query = entityManager.createQuery(queryParameters);

            return (List<Address>) query.getResultList();
        }
        return Collections.emptyList();
    }
}
