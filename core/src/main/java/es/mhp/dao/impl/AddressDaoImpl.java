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

    public List<Address> findAny(Address address) {
        return findAll(address, false);
    }

    public List<Address> findAll(Address address) {
        return findAll(address, true);
    }

    public List<Address> findAll() {
        Query query = entityManager.createQuery("SELECT a FROM ADDRESS a");
        return query.getResultList();
    }

    @Override
    protected List<Address> findAll(Address address, boolean type) {
        String contatenator = type ? " AND ": " OR ";

        if (address != null) {
            String queryParameters = "SELECT a FROM ADDRESS a WHERE ";

            if (!StringUtils.isEmpty(address.getMainStreet())) {
                queryParameters = "STREET1 = " + address.getMainStreet() + contatenator;
            }

            if (!StringUtils.isEmpty(address.getSecondaryStreet())) {
                queryParameters = "STREET2 = " + address.getSecondaryStreet() + contatenator;
            }

            if (!StringUtils.isEmpty(address.getCity())) {
                queryParameters = "CITY = " + address.getCity() + contatenator;
            }

            if (!StringUtils.isEmpty(address.getState())) {
                queryParameters = "STATE = " + address.getState() + contatenator;
            }

            if (!StringUtils.isEmpty(address.getLatitude()) && !StringUtils.isEmpty(address.getLongitude())) {
                queryParameters = "LATITUDE = " + address.getLatitude() +
                        "AND LONGITUDE = " + address.getLongitude() + " AND ";
            }

            queryParameters = replaceLast(queryParameters, contatenator, "");
            Query query = entityManager.createQuery(queryParameters);

            return (List<Address>) query.getResultList();
        }
        return Collections.emptyList();
    }

    private String replaceLast(String string, String substring, String replacement)
    {
        int index = string.lastIndexOf(substring);
        if (index == -1)
            return string;
        return string.substring(0, index) + replacement
                + string.substring(index+substring.length());
    }
}
