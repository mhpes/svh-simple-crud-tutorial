package es.mhp.services.impl;

import es.mhp.entities.Address;
import es.mhp.repositories.custom.AddressRepository;
import es.mhp.repositories.ZipLocationRepository;
import es.mhp.services.IAddressService;
import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */

@Service
@Transactional
@Configuration
@EnableJpaRepositories
public class ServiceAddressImpl implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ZipLocationRepository zipLocationRepository;

    @Override
    public Set<AbstractDTO> findAll() {
        Iterable<Address> addressSet = addressRepository.findAll();

        Set<AbstractDTO> addressDTOs = new HashSet<>();

        for (Address address : addressSet){
            addressDTOs.add(new AddressDTO(address));
        }

        return addressDTOs;
    }

    @Override
    public Set<AbstractDTO> findAllAddresses(AddressDTO addressDTO) {
        Set<Address> addressSet = addressRepository.findAll(addressDTO.toEntity());

        if (!addressSet.isEmpty()){
            Set<AbstractDTO> addressDTOs = new HashSet<>();

            for (Address currentAddress : addressSet) {
                addressDTOs.add(new AddressDTO(currentAddress));
            }

            return addressDTOs;
        }
        return Collections.emptySet();
    }

    @Override
    public Set<AbstractDTO> findAnyAddresses(AddressDTO addressDTO) {
        Set<Address> addressSet = addressRepository.findAny(addressDTO.toEntity());

        if (!addressSet.isEmpty()){
            Set<AbstractDTO> addressDTOs = new HashSet<>();

            for (Address currentAddress : addressSet) {
                addressDTOs.add(new AddressDTO(currentAddress));
            }

            return addressDTOs;
        }
        return Collections.emptySet();
    }

    @Override
    public Set<String> getStateList() {
        return addressRepository.findAllStates();
    }

    @Override
    public AddressDTO save(AddressDTO addressDto) {
        return new AddressDTO(addressRepository.save(addressDto.toEntity()));
    }

    @Override
    public void delete(Object id) {
        addressRepository.delete((Integer) id);
    }
}
