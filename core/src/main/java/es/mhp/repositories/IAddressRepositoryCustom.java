package es.mhp.repositories;

import es.mhp.entities.Address;

import java.util.Set;

/**
 * Created by Edu on 11/03/2016.
 */

public interface IAddressRepositoryCustom {
    Set<Address> findAny(Address address);
    Set<Address> findAll(Address address);
}
