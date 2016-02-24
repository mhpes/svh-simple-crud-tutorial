package es.mhp.services.impl;

import entities.SellerContactInfo;
import es.mhp.dao.ISellerDao;
import es.mhp.services.ISellerContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Edu on 24/02/2016.
 */
@Service
public class ServiceSellerContactInfoImpl implements ISellerContactInfoService {

    @Autowired
    private ISellerDao iSellerDao;


    public List<SellerContactInfo> findAllSellers() {
        return iSellerDao.findAll();
    }

    public List<SellerContactInfo> findAllSellers(SellerContactInfo sellerContactInfo) {
        return iSellerDao.findAll(sellerContactInfo);
    }

    public List<SellerContactInfo> findAnySeller(SellerContactInfo sellerContactInfo) {
        return iSellerDao.findAny(sellerContactInfo);
    }

    public SellerContactInfo update(SellerContactInfo sellerContactInfo) {
        return iSellerDao.update(sellerContactInfo);
    }

    public void delete(SellerContactInfo sellerContactInfo) {
        iSellerDao.delete(sellerContactInfo);
    }

    public SellerContactInfo findSellerById(long id) {
        return iSellerDao.findById(id);
    }
}
