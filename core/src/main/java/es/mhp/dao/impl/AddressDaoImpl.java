package es.mhp.dao.impl;


import entities.Address;
import entities.ZipLocation;
import es.mhp.dao.IAddressDao;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Edu on 12/02/2016.
 */

@Component
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

    public String test() {
        return "Edumola";
    }

    @Override
    public List<Address> findAllAddressMocked() {
        List<Address> addresses = new ArrayList<>();

        addresses.add(new Address(1, "MainStreet", "SecondaryStreet", "Tenerife", "Canarias", BigDecimal.ONE, BigDecimal.TEN, new ZipLocation(1, 8822, "S/C de Tenerife", "Canary Islands")));
        addresses.add(new Address(2, "MainStreet_1", "SecondaryStreet_1", "Las Palmas", "Canarias", BigDecimal.ONE, BigDecimal.TEN, new ZipLocation(1, 32108, "Arico powah", "Canary Islands")));
        addresses.add(new Address(3, "MainStreet_2", "SecondaryStreet_2", "El Hierro", "Canarias", BigDecimal.ONE, BigDecimal.TEN, new ZipLocation(1, 38108, "Añaza", "Canary Islands")));

        return addresses;
    }
}
