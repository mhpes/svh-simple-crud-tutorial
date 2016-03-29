package es.mhp.services;

import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.AddressDTO;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */

public interface IAddressService extends AbstractService {
    Set<AbstractDTO> findAllAddresses(AddressDTO addressDTO);
    Set<AbstractDTO> findAnyAddresses(AddressDTO addressDTO);
    AddressDTO save(AddressDTO addressDTO);
    Set<String> getStateList();
}
