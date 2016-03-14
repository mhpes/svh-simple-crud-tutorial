package es.mhp.repositories.impl;

import es.mhp.entities.Address;
import es.mhp.repositories.IAddressRepositoryCustom;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edu on 11/03/2016.
 */

@Component
public class AddressRepositoryImpl implements IAddressRepositoryCustom {

    @PersistenceContext(unitName = "PetshopUnit")
    private EntityManager entityManager;

    @Override
    public Set<Address> findAny(Address address) {
        return findAll(address, false);
    }

    @Override
    public Set<Address> findAll(Address address) {
        return findAll(address, true);
    }

    private Set<Address> findAll(Address address, boolean type) {
        String contatenator = type ? " AND " : " OR ";

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

            queryParameters = replaceLast(queryParameters, contatenator, "");
            return new HashSet<>(entityManager.createQuery(queryParameters).getResultList());
        }
        return Collections.emptySet();
    }

    public EntityManager getEntityManager() {
        return getEntityManager();
    }

    public static String replaceLast(String text, String regex, String replacement) {
        return text.replaceFirst("(?s)"+regex+"(?!.*?"+regex+")", replacement);
    }
}
