package es.mhp.services;

import es.mhp.entities.SellerContactInfo;

import java.util.List;

/**
 * Created by Edu on 24/02/2016.
 */
public interface ISellerContactInfoService {

    List<SellerContactInfo> findAllSellers();
    List<SellerContactInfo> findAllSellers(SellerContactInfo sellerContactInfo);
    List<SellerContactInfo> findAnySeller(SellerContactInfo sellerContactInfo);
    SellerContactInfo update(SellerContactInfo sellerContactInfo);
    void delete(SellerContactInfo sellerContactInfo);
    SellerContactInfo findSellerById(long id);
}
