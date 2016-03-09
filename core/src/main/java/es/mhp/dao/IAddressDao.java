package es.mhp.dao;

import es.mhp.entities.Address;

import java.util.Set;

/**
 * Created by Edu on 12/02/2016.
 */
public interface IAddressDao extends IPetshopGenericDao<Address> {
    void deleteById(int id);
    Address findById(int id);
    Set<String> findAllStates();
}
