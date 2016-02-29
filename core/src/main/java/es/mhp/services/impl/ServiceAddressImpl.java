package es.mhp.services.impl;

import es.mhp.dao.IAddressDao;
import es.mhp.entities.Address;
import es.mhp.entities.ZipLocation;
import es.mhp.services.dto.AddressDTO;
import es.mhp.services.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */

@Service
@Transactional
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
    public AddressDTO save(AddressDTO addressDto) {
        Address address = iAddressDao.findById(addressDto.getAddressId());

        if (address != null){
            iAddressDao.update(addressDto.ToEntity(address));
        } else {
            address = new Address();
            iAddressDao.save(address);
        }
        return addressDto;
    }

    @Override
    public void delete(AddressDTO addressDto) {
        iAddressDao.deleteById(addressDto.getAddressId());
    }

    @Override
    public AddressDTO findAddressById(long id) {
        return new AddressDTO(iAddressDao.findById(id));
    }
}
