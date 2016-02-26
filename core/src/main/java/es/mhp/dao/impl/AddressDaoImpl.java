package es.mhp.dao.impl;


import es.mhp.dao.IAddressDao;
import es.mhp.entities.Address;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Edu on 12/02/2016.
 */

@Component
public class AddressDaoImpl extends AbstractPetshopGenericDao<Address> implements IAddressDao {

    @Override
    public Address findById(long id) {
        return entityManager.find(Address.class, id);
    }

    @Override
    public Set<Address> findAny(Address entity) {
        return findAll(entity, false);
    }

    @Override
    public Set<Address> findAll(Address entity) {
        return findAll(entity, true);
    }

    @Override
    public Set<Address> findAll() {
        List<Address> addressList = entityManager.createQuery("SELECT a FROM Address a").getResultList();
        return new HashSet<Address>(addressList);
    }

    @Override
    protected Set<Address> findAll(Address entity, boolean type) {
        String contatenator = type ? " AND ": " OR ";

        if (entity != null) {
            String queryParameters = "SELECT a FROM Address a WHERE ";

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

            return (Set<Address>) query.getResultList();
        }
        return Collections.emptySet();
    }
}
