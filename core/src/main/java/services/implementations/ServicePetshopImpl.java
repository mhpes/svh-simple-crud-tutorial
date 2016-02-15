package services.implementations;

import daos.IAdressDao;
import entities.Address;
import org.springframework.beans.factory.annotation.Autowired;
import services.interfaces.IServicePetshop;

import java.util.List;

/**
 * Created by Edu on 15/02/2016.
 */
public class ServicePetshopImpl implements IServicePetshop {

    @Autowired
    private IAdressDao addressDao;

    public List<Address> findAllAddress() {
        return addressDao.findAll();
    }
}
