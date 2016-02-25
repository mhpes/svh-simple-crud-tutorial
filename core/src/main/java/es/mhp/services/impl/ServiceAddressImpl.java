package es.mhp.services.impl;

import es.mhp.entities.Address;
import es.mhp.dao.IAddressDao;
import es.mhp.services.IAdressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Edu on 24/02/2016.
 */

@Service
public class ServiceAddressImpl implements IAdressService {

    @Autowired
    private IAddressDao iAddressDao;

    public List<Address> findAllAddresses() { return iAddressDao.findAll(); }

    public List<Address> findAllAddresses(Address address) { return iAddressDao.findAll(address); }

    public List<Address> findAnyAddresses(Address address) { return iAddressDao.findAny(address); }

    public Address update(Address address) { return iAddressDao.update(address); }

    public void delete(Address address) { iAddressDao.delete(address); }

    public Address findAddressById(long id) { return iAddressDao.findById(id); }
}
