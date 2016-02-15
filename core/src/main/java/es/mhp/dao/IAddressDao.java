package es.mhp.dao;

import entities.Address;

import java.util.List;

/**
 * Created by Edu on 12/02/2016.
 */
public interface IAddressDao extends IAbstractDao{
    List<Address> findAll(Address address);
    List<Address> findAny(Address address);
}
