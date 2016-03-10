package es.mhp.services.impl;

import es.mhp.dao.IAddressDao;
import es.mhp.entities.Address;
import es.mhp.services.IAddressService;
import es.mhp.services.dto.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ServiceAddressImpl implements IAddressService {

    @Autowired
    private IAddressDao iAddressDao;

    /*@Autowired
    private ZipLocationRepository iZipLocationRepository;*/

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
    public Set<AddressDTO> findAllAddresses(AddressDTO addressDTO) {
        Set<Address> addressSet = iAddressDao.findAll(addressDTO.toEntity(new Address()));

        Set<AddressDTO> addressDTOs = new HashSet<>();

        for (Address currentAddress : addressSet) {
            addressDTOs.add(new AddressDTO(currentAddress));
        }

        return addressDTOs;
    }

    @Override
    public Set<AddressDTO> findAnyAddresses(AddressDTO addressDTO) {
        Set<Address> addressSet = iAddressDao.findAny(addressDTO.toEntity(new Address()));

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
    public Set<AddressDTO> findAnyAddresses(String text) {
        Set<Address> addressSet = iAddressDao.findAny(text);

        Set<AddressDTO> addressDTOs = new HashSet<>();

        for (Address currentAddress : addressSet) {
            addressDTOs.add(new AddressDTO(currentAddress));
        }

        return addressDTOs;
    }

    @Override
    public Set<String> stateList() {
        return iAddressDao.findAllStates();
    }

    @Override
    public AddressDTO save(AddressDTO addressDto) {
        Address address = iAddressDao.findById(addressDto.getAddressId());
        //ZipLocation zipLocation = iZipLocationRepository.findById(addressDto.getZip());

        if (address != null){

            /*if (zipLocation == null){
                zipLocation = new ZipLocation(addressDto.getZip(), addressDto.getCity(), addressDto.getState());
                iZipLocationRepository.save(zipLocation);
            }*/

            //address.setZipLocation(zipLocation);
            iAddressDao.update(addressDto.toEntity(address));
        } else {
            address = new Address();

            /*if (zipLocation == null){
                zipLocation = new ZipLocation(addressDto.getZip(), addressDto.getCity(), addressDto.getState());
                iZipLocationRepository.save(zipLocation);
            }*/

            //address.setZipLocation(zipLocation);
            iAddressDao.save(addressDto.toEntity(address));
        }
        return new AddressDTO(address);
    }

    @Override
    public void delete(AddressDTO addressDto) {
        iAddressDao.deleteById(addressDto.getAddressId());
    }

    @Override
    public AddressDTO findAddressById(int id) {
        return new AddressDTO(iAddressDao.findById(id));
    }
}
