package services.impl;

import entities.Address;
import es.mhp.dao.IAddressDao;
import org.springframework.beans.factory.annotation.Autowired;
import services.IServicePetshop;

import java.util.List;

/**
 * Created by Edu on 15/02/2016.
 */
public class ServicePetshopImpl implements IServicePetshop {

    @Autowired
    private IAddressDao addressDao;

    public List<Address> findAllAddress() {
        return addressDao.findAll();
    }
}
