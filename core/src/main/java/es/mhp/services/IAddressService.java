package es.mhp.services;

import es.mhp.entities.Address;
import es.mhp.services.dto.AddressDTO;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */

public interface IAddressService {
    Set<AddressDTO> findAllAddresses();
    Set<AddressDTO> findAllAddresses(Address address);
    Set<AddressDTO> findAnyAddresses(Address address);
    AddressDTO update(Address address);
    void delete(Address address);
    AddressDTO findAddressById(long id);
}
