package es.mhp.repositories.impl;

import es.mhp.entities.Address;
import es.mhp.repositories.ICustomAddressRepository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edu on 11/03/2016.
 */

public class CustomAddressRepositoryImpl implements ICustomAddressRepository {

    @PersistenceContext(unitName = "PetshopUnit")
    private EntityManager entityManager;

    @Override
    public Set<Address> findAny(Address address) {
        return null;
    }

    @Override
    public Set<Address> findAll(Address address) {
        String contatenator = " AND ";

        if (address != null) {
            String queryParameters = "SELECT a FROM Address a WHERE ";

            if (!StringUtils.isEmpty(address.getMainStreet())) {
                queryParameters += "STREET1 = '" + address.getMainStreet() + "'" + contatenator;
            }

            if (!StringUtils.isEmpty(address.getSecondaryStreet())) {
                queryParameters += "STREET2 = '" + address.getSecondaryStreet() + "'" + contatenator;
            }

            if (!StringUtils.isEmpty(address.getCity())) {
                queryParameters += "CITY = '" + address.getCity() + "'" + contatenator;
            }

            if (!StringUtils.isEmpty(address.getState())) {
                queryParameters += "STATE = '" + address.getState()+ "'" + contatenator;
            }

            /*queryParameters = replaceLast(queryParameters, contatenator, "");*/
            return new HashSet<>(getEntityManager().createQuery(queryParameters).getResultList());
        }
        return Collections.emptySet();
    }

    public EntityManager getEntityManager() {
        return getEntityManager();
    }
}
