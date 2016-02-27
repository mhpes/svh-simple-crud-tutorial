package es.mhp.services.impl;

import es.mhp.dao.IAddressDao;
import es.mhp.entities.Address;
import es.mhp.services.dto.AddressDTO;
import es.mhp.services.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */

@Service
public class ServiceAddressImpl implements IAddressService {

    @Autowired
    private IAddressDao iAddressDao;

    @Override
    public Set<AddressDTO> findAllAddresses() {
        Set<Address> addressSet = iAddressDao.findAll();

        Set<AddressDTO> addressDTOs = new HashSet<>();

        for (Address address : addressSet){
            addressDTOs.add(new AddressDTO(address));
        }

        return addressDTOs;
    }

    @Override
    public Set<AddressDTO> findAllAddresses(Address address) {
        Set<Address> addressSet = iAddressDao.findAll(address);

        Set<AddressDTO> addressDTOs = new HashSet<>();

        for (Address currentAddress : addressSet) {
            addressDTOs.add(new AddressDTO(currentAddress));
        }

        return addressDTOs;
    }

    @Override
    public Set<AddressDTO> findAnyAddresses(Address address) {
        Set<Address> addressSet = iAddressDao.findAny(address);

        if (!addressSet.isEmpty()){
            Set<AddressDTO> addressDTOs = new HashSet<>();

            for (Address currentAddress : addressSet) {
                addressDTOs.add(new AddressDTO(currentAddress));
            }

            return addressDTOs;
        }
        return Collections.emptySet();
    }

    @Override
    public AddressDTO update(AddressDTO addressDto) {
        return new AddressDTO();
    }

    @Override
    public void delete(AddressDTO addressDto) {
        iAddressDao.delete(addressDto.ToEntity());
    }

    @Override
    public AddressDTO findAddressById(long id) {
        return new AddressDTO(iAddressDao.findById(id));
    }
}
