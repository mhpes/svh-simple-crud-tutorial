package es.mhp.services;

import es.mhp.entities.SellerContactInfo;
import es.mhp.services.dto.SellerContactInfoDTO;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */
public interface ISellerContactInfoService {

    Set<SellerContactInfoDTO> findAllSellers();
    Set<SellerContactInfoDTO> findAllSellers(SellerContactInfo sellerContactInfo);
    Set<SellerContactInfoDTO> findAnySeller(SellerContactInfo sellerContactInfo);
    SellerContactInfoDTO update(SellerContactInfo sellerContactInfo);
    void delete(SellerContactInfo sellerContactInfo);
    SellerContactInfoDTO findSellerById(long id);
}
