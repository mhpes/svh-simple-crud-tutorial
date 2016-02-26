package es.mhp.services;

import es.mhp.entities.SellerContactInfo;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */
public interface ISellerContactInfoService {

    Set<SellerContactInfo> findAllSellers();
    Set<SellerContactInfo> findAllSellers(SellerContactInfo sellerContactInfo);
    Set<SellerContactInfo> findAnySeller(SellerContactInfo sellerContactInfo);
    SellerContactInfo update(SellerContactInfo sellerContactInfo);
    void delete(SellerContactInfo sellerContactInfo);
    SellerContactInfo findSellerById(long id);
}
