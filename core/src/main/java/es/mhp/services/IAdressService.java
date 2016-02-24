package es.mhp.services;

import entities.Address;

import java.util.List;

/**
 * Created by Edu on 24/02/2016.
 */

public interface IAdressService {
    List<Address> findAllAddresses();
    List<Address> findAllAddresses(Address address);
    List<Address> findAnyAddresses(Address address);
    Address update(Address address);
    void delete(Address address);
    Address findAddressById(long id);
}
