package es.mhp.dao.impl;


import es.mhp.dao.IAddressDao;
import es.mhp.entities.Address;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edu on 12/02/2016.
 */

@Component
@Transactional
public class AddressDaoImpl extends AbstractPetshopGenericDao<Address> implements IAddressDao {

    @Override
    public Address findById(int id) {
        return getEntityManager().find(Address.class, id);
    }

    @Override
    public Set<Address> findAny(String text) {
        String concatenator = " OR ";

        if (text != null) {
            String queryParameters = "SELECT a FROM Address a WHERE ";

            queryParameters += "STREET1 like '%" + text + "%' " + concatenator;
            queryParameters += "STREET2 like '%" + text + "%' " + concatenator;
            queryParameters += "CITY like '%" + text + "%' " +  concatenator;
            queryParameters += "STATE like '%" + text + "%' " + concatenator;
            queryParameters += "LATITUDE like '%" + text + "%' " + concatenator + " LONGITUDE like '%" + text + "%' ";

            return new HashSet (getEntityManager().createQuery(queryParameters).getResultList());
        }
        return Collections.emptySet();
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
        return new HashSet<> (getEntityManager().createQuery("SELECT a FROM Address a ORDER BY addressid").getResultList());
    }

    public Set<String> findAllStates(){
        return new HashSet<> (getEntityManager().createQuery("SELECT DISTINCT a.state FROM Address a").getResultList());
    }

    @Override
    protected Set<Address> findAll(Address entity, boolean type) {
        String contatenator = type ? " AND ": " OR ";

        if (entity != null) {
            String queryParameters = "SELECT a FROM Address a WHERE ";

            if (!StringUtils.isEmpty(entity.getMainStreet())) {
                queryParameters += "STREET1 = '" + entity.getMainStreet() + "'" + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getSecondaryStreet())) {
                queryParameters += "STREET2 = '" + entity.getSecondaryStreet() + "'" + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getCity())) {
                queryParameters += "CITY = '" + entity.getCity() + "'" + contatenator;
            }

            if (!StringUtils.isEmpty(entity.getState())) {
                queryParameters += "STATE = '" + entity.getState()+ "'" + contatenator;
            }

            queryParameters = replaceLast(queryParameters, contatenator, "");
            return new HashSet<> (getEntityManager().createQuery(queryParameters).getResultList());
        }
        return Collections.emptySet();
    }

    @Override
    public void deleteById(int id) {
        Address address = findById(id);

        if (address != null) {
            getEntityManager().remove(address);
        }
    }
}
