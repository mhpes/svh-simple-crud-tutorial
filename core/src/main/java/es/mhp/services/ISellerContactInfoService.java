package es.mhp.services;

import es.mhp.services.dto.SellerContactInfoDTO;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */
public interface ISellerContactInfoService {

    Set<SellerContactInfoDTO> findAllSellers();
    Set<SellerContactInfoDTO> findAllSellers(SellerContactInfoDTO sellerContactInfoDTO);
    Set<SellerContactInfoDTO> findAnySellers(SellerContactInfoDTO sellerContactInfoDTO);
    SellerContactInfoDTO save(SellerContactInfoDTO sellerContactInfoDTO);
    void delete(SellerContactInfoDTO sellerContactInfoDTO);
    SellerContactInfoDTO findSellerById(int id);
}
