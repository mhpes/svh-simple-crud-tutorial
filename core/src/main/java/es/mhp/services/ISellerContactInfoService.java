package es.mhp.services;

import es.mhp.services.dto.AbstractDTO;
import es.mhp.services.dto.SellerContactInfoDTO;

import java.util.Set;

/**
 * Created by Edu on 24/02/2016.
 */
public interface ISellerContactInfoService extends AbstractService {
    Set<AbstractDTO> findAllSellers();
    Set<AbstractDTO> findAnySellers(String text);
    SellerContactInfoDTO save(SellerContactInfoDTO sellerContactInfoDTO);
}
