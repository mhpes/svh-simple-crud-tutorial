package es.mhp.dao;

import entities.Address;

import java.util.List;

/**
 * Created by Edu on 12/02/2016.
 */
public interface IAddressDao extends IPetshopGenericDao<Address> {
    String test();
    List<Address> findAllAddressMocked();
}
