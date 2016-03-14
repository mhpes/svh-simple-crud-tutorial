package es.mhp.services;

import es.mhp.services.dto.AddressDTO;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */

public interface IAddressService extends AbstractService{
    Set<AddressDTO> findAllAddresses();
    Set<AddressDTO> findAllAddresses(AddressDTO addressDTO);
    Set<AddressDTO> findAnyAddresses(AddressDTO addressDTO);
    Set<String> stateList();
    void save(AddressDTO addressDTO);
    void delete(AddressDTO addressDTO);
}
